package com.example.demo6.security;

import com.example.demo6.dao.MemberDao;
import com.example.demo6.util.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 로그인에 실패하면 "로그인에 몇 회 실패했습니다" 라고 출력
// 로그인에 5번 실패하면 계정을 블록한 다음("계정이 블록되었습니다" 라고 출력)

@Component
public class Demo6AuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Autowired
  private MemberDao memberDao;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    // 계정이 블록되어 실패 : 403(권한이 필요)
    // 아이디나 비밀번호가 틀려서 실패 : 401(로그인이 필요)
    if (exception instanceof LockedException) {
      ResponseUtil.sendJsonResponse(response, 403, "블록된 계정");
    } else {
      ResponseUtil.sendJsonResponse(response, 401, "로그인 실패");
    }
  }
}

