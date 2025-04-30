package com.example.demo05.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.access.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

import java.io.*;

// 403 이 발생했을 때 동작할 핸들러
// redirectAttribute 사용 불가능
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    // 사용자 정보를 저장하는 곳
    // request : 주소가 바뀌면 (하나의 작업이 끝나면) 사라진다
    // session : 사용자가 로그아웃할 때까지 유지된다 (ex. 장바구니, 로그인 여부)
    // database : 영구히 유지된다

    // request 에 저장된 정보는 redirect 하고 나면 사용 불가능
    //request.setAttribute("msg", "잘못된 접근입니다.");

//    HttpSession session = request.getSession();
//    session.setAttribute("msg", "잘못된 접근입니다");
    // 일반적인 예외들은 Spring Se
    response.sendRedirect("/");
  }
}
