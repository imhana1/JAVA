package com.example.demo6.dao;

import com.example.demo6.dto.MemberDto;
import com.example.demo6.entity.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Optional;

@Mapper
public interface MemberDao {
  @Select("select count(*) from members where username=#{username}")
  boolean existsByUsername(String username);

  int save(Member member);

  @Update("update members set IS_LOCK = 0 where CODE=#{code} and rownum = 1")
  int verifyCode(String code);

  @Select("select username from members where email=#{email} and rownum=1")
  Optional<String> findUsernameByEmail(String email);

  @Update("update members set password=#{newPassword} where username=#{username}")
  int updatePassword(String username, String newPassword);

  @Select("select username, password, role, is_lock from members where username=#{username}")
  Optional<Member> loadLoginData(String username);

  @Select("select failed_attempts from members where username=#{username}")
  Optional<Integer> 로그인실패횟수 (String name);

  @Update("update members set failed_attempts=failed_attempts+1 where username=#{username}")
  int 로그인실패횟수증가(String 로그인_시도_아이디);

  @Update("update members set is_Lock=1 where username=#{username}")
  int 계정블록(String 로그인_시도_아이디);

  @Update("update members set failed_attempts=0 where username=#{username}")
  int reset로그인실패횟수(String loginId);

  @Select("select username, email, profile, join_day, levels from members where username=#{loginId}")
  Member findByUsername(String loginId);

  @Select("select password from members where username=#{loginId}")
  String findPasswordByUsername(String loginId);

  @Delete("delete from members where username=#{loginId}")
  int delete(String loginId);

  @Update("update MEMBERS set PROFILE=#{profile} where USERNAME=#{loginId}")
  int updateProfile(String profile, String loginId);

  // 로그인은 swagger로 테스트 못해 (SecuriConfig에 저렇게 만들어놔서)
}
