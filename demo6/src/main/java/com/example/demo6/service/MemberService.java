package com.example.demo6.service;

import com.example.demo6.dao.MemberDao;
import com.example.demo6.dto.MemberDto;
import com.example.demo6.entity.Member;
import com.example.demo6.util.Demo6Util;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class MemberService {
  @Autowired
  private MemberDao memberDao;
  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  private JavaMailSender mailSender;

  public void sendMail(String 보낸이, String 받는이, String 제목, String 내용) {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
      helper.setFrom(보낸이);
      helper.setTo(받는이);
      helper.setSubject(제목);
      helper.setText(내용, true);
      // 두번째 파라미터는 html 활성화 여부 <a href = 'aaa'>링크</a>
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    mailSender.send(mimeMessage);
  }

  public boolean checkUsername(MemberDto.UsernameCheck dto) {
    return !memberDao.existsByUsername(dto.getUsername());
  }

  public Member signup(MemberDto.Create dto) {
    // 컨트롤러 : DTO 로 작업 (DTO 는 화면을 따라 간다)
    // 데이터베이스는 가급적 entity 로 작업 (Entity 는 db 를 따라간다)
    // 개발자는 가운데에서 번역해주는 역할을 한다고 생각하면 됨 (Entity 와 Dto 사이의 Service 역할)

    // 비밀번호 암호화했다고 치자
//    String encodedPassword = dto.getPassword();
//    Member member = dto.toEntity(encodedPassword);
//    memberDao.save(member);
//    return member;

    // 1. 비밀번호 암호화
    String encodedPassword = encoder.encode(dto.getPassword());

    // 2. 프사를 업로드 했다면 저장을 위해 base64 인코딩, 프사 확인 (업로드하지 않았다면 기본 프사를 저장)
    MultipartFile profile = dto.getProfile();
    // 프론트에 <input type='file' name='profile'> 이 없다면 profile 이 null 이 된다
    // 이 경우 profile.isEmpty() 는 null pointer exception(NPE)
    boolean 프사_존재 = profile!=null && !profile.isEmpty();
    // <input type='file' name='profile'> 이렇게 input 이 있지만 선택은 하지 않음 → null은 아님
    // <input type='text' name='username'> → 입력을 하지 않았다 → 서버에서 꺼내면 null 이 아니라 ""
    String base64Image = "";
    try {
      if(프사_존재) {
        // 사용자가 업로드한 이미지를 base64 로 바꾸는 함수는 실패할 수 있다
        base64Image = Demo6Util.convertToBase64(profile);
      } else {
        base64Image = Demo6Util.getDefaultBase64Profile();
      }
    } catch(IOException e) {

    }
    // 3. 암호화된 비밀번호, base64 이미지, 랜덤한 체크코드를 가지고 dto 를 member 로 변환
    String code = RandomStringUtils.secure().nextAlphanumeric(20);
    Member member = dto.toEntity(encodedPassword, base64Image, code);

    // 4. 이메일 발송
    String checkUrl = "http://localhost:8080/api/members/verify?code=" + code;
    String html = "<p>가입해주셔서 감사합니다</p>";
    html += "<p>아래의 링크를 클릭하면 가입이 완료됩니다</p>";
    html += "<a href='" + checkUrl + "'>링크</a>";

    memberDao.save(member);
    sendMail("admin@icia.com", member.getEmail(), "가입확인메일", html);
    return member;
  }

  public boolean verify(String code) {
    return memberDao.verifyCode(code) == 1;
  }

  public Optional<String> searchUsername(String email) {
    return memberDao.findUsernameByEmail(email);
  }

  public boolean getTemporaryPassword(MemberDto.FindPassword dto) {
    Member member = memberDao.findByUsername(dto.getUsername());
    if(member == null)
      return false;
    String newPassword = RandomStringUtils.secure().nextAlphanumeric(10);
    memberDao.updatePassword(dto.getUsername(), encoder.encode(newPassword));

    String html = "<p>아래 임시 비밀번호로 로그인 하세요</p>";
    html += "<p>" + newPassword + "</p>";
    sendMail("admin@icia.com", member.getEmail(), "임시 비밀번호", html);
    return true;
  }

  public boolean checkPassword(MemberDto.CheckPassword dto, String loginId) {
    String encodedPassword = memberDao.findPasswordByUsername(loginId);
    if (encodedPassword == null)
      return false;
    return encoder.matches(dto.getPassword(), encodedPassword);
  }

  public MemberDto.Read read(String loginId) {
    Member member = memberDao.findByUsername(loginId);
    return member.toRead();
  }

  public boolean changePassword(MemberDto.PasswordChange dto, String loginId) {
    // 기존 암호화된 비밀번호를 읽어와 비밀번호가 맞는 지 확인하는 과정이 필요함
    // 비밀번호가 일치한 경우 새 비밀번호로 업데이트 → 틀리면 false
    String encodedPassword = memberDao.findPasswordByUsername(loginId);
    if(encoder.matches(dto.getCurrentPassword(), encodedPassword))
      return false;
    // 비밀번호가 일치한 경우 새 비밀번호로 업데이트
    return memberDao.updatePassword(loginId, encoder.encode(dto.getNewPassword()))==1;
  }

  public void resign(String loginId) {
    memberDao.delete(loginId);
  }
}
