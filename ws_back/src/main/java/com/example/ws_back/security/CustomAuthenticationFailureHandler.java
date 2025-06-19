package com.example.ws_back.security;

import com.example.ws_back.util.ResponseUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import java.io.*;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    if(exception instanceof LockedException)
      ResponseUtil.sendJsonResponse(response, 403, "비활성화된 계정입니다");
    else
      ResponseUtil.sendJsonResponse(response, 401, "아이디나 비밀번호를 확인하세요");
  }
}
