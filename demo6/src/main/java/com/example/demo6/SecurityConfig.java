package com.example.demo6;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
// final 로 선언한 필드를 대상으로 하는 생성자를 만들어준다 - 스프링에서 생성자를 이용해 객체를 주입할 때 사용 가능
@RequiredArgsConstructor
public class SecurityConfig {
  // 401 오류 처리 (로그인 필요)
  private final AuthenticationEntryPoint authenticationEntryPoint;

  // 403 오류 처리 (권한 오류)
  private final AccessDeniedHandler accessDeniedHandler;

  // 로그인 성공 - 200으로 응답
  private final AuthenticationSuccessHandler authenticationSuccessHandler;

  // 로그인 실패 - 409로 응답
  private final AuthenticationFailureHandler authenticationFailureHandler;

  // 로그아웃 성공 - 200으로 응답
  private final LogoutSuccessHandler logoutSuccessHandler;

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // 스프링 시큐리티는 1개의 필터들의 집합체 (FilterChain)
  // 필터를 생성, 등록하는 설정 함수
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity config) throws Exception {
    // csrf : MVC 방식에서 타임리프 파일을 위조, 변조하는 것을 막기 위해 사용한다
    //        사용자가 작업한 html 파일이 서버가 보내준 파일이 맞는지, 혹시 사용자 html 을 조작하지 않았는지 확인하기 위한 랜덤 문자열
    //        화면이 없는 rest 에는 의미없는 개념
    config.csrf(csrf -> csrf.disable());

    // 화면에 아이디와 비밀번호를 입력해서 로그인하는 formLogin 을 활성화
    config.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
            .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler));
    config.logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler));
    config.exceptionHandling(handler ->
            handler.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint));
    return config.build();
  }

}





