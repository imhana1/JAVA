package com.example.ws_back.security;

import com.example.ws_back.util.ResponseUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    // 아이디와 권한을 응답한다
    // Authentication : 인증 정보를 담는 표준 인터페이스
    String username = authentication.getName();

    // Authentication.getAuthorities() : 표준 권한 인터페이스 Authority들의 리스트를 리턴
    //                                   스프링 권한은 누적되지 않는다
    //                                   ROLE_ADMIN은 ROLE_USER 권한을 포함한다 -> 이런거 지원하지 않는다
    //                                   만약 두 권한의 작업을 모두 하려면 ROLE_ADMIN, ROLE_USER 모두 가져야 한다 -> 권한은 ArrayList

    // map은 파라미터를 입력받아 다른 객체로 변환하는 함수
    // map(authority->authority.getAuthority()) 이건 뭐지?
    // - authority는 스프링 시큐리티 권한 객체
    // - authority.getAuthority()는 "ROLE_USER", "ROLE_ADMIN"과 같은 문자열
    String role = authentication.getAuthorities().stream().map(a->a.getAuthority()).findFirst().orElse("");

    Map<String, String> responseBody = Map.of("username", username,"role", role);
    ResponseUtil.sendJsonResponse(response, 200, responseBody);
  }
}








