package com.example.demo05.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import java.io.*;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    // 로그인에 성공하면 루트 페이지로 이동
    // 작업 흐름
      // 1. 글쓰기를 선택
      // 2. SS 가 로그인이 필요하다는 판단을 함
      // 3. 로그인 페이지로 이동
      // 4. 로그인
      // 5. SS 가 글쓰기로 이동시켜준다
    // 개발자가 로그인 성공 핸들러를 만들었네...... 그럼 SS 는 아무것도 하지 않음
    // 모든 걸 정해줘야함
    response.sendRedirect("/");
  }
}
