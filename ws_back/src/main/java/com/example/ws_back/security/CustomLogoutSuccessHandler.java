package com.example.ws_back.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.stereotype.*;

import java.io.*;

// MVC 방식 SS 의 기본 구현은 로그아웃에 성공하면 루트페이지로 "이동" 한다
// REST 방식 : "백에서 redirect 한다" 는 개념이 존재할 수 없다 → 반드시 변경해야한다
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

  }
}
