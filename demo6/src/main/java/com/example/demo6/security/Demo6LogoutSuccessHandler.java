package com.example.demo6.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// MVC 방식 SS 의 기본 구현은 로그아웃에 성공하면 루트페이지로 "이동" 한다
// REST 방식 : "백에서 redirect 한다" 는 개념이 존재할 수 없다 → 반드시 변경해야한다
@Component
public class Demo6LogoutSuccessHandler implements LogoutSuccessHandler {
  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

  }
}
