package com.example.demo6.security;

import com.example.demo6.dao.MemberDao;
import com.example.demo6.util.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Demo6AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  @Autowired
  private MemberDao memberDao;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    // 로그인 실패 횟수 리셋
    String loginId = authentication.getName();
    memberDao.reset로그인실패횟수(loginId);
    ResponseUtil.sendJsonResponse(response, 200, loginId);
  }
}
