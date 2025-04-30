package com.example.demo05;


import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;
import org.springframework.security.web.access.*;
import org.springframework.security.web.authentication.*;

// 아래 클래스는 스프링 설정 파일이라고 선언한 것
// @Component, @Controller, @Service, @Repository 는 스프링이 객체를 생성해서 관리 (스프링 빈)
// @Mapper 는 마이바티스가 객체를 생성해서 스프링에 위탁
// @Configuration 은 앱이 실행될 때 스프링이 읽어와 설정을 잡는 클래스
// @Bean
  // ㄴ 스프링 빈을 생성하려면 @Component 를 사용하면 된다 → 이럴려면 내가 코드 편집 권한이 있어야함
  // ㄴ 스프링 시큐리티는 비밀번호 암호화가 필수 → 비밀번호 암호화 객체가 필요
  // ㄴ BCrypt 가 스프링 전용인가? 아니다.
  // ㄴ 그래서 @Component 가 붙어있지 않다

// prePostEnabled 는 자동으로 켜져 있기 때문에 어두운 색으로 변해있음
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class SecurityConfig {
  // 메소드의 리턴 객체를 스프링빈으로 만들어라
  // 내가 남의 라이브러리를 갖다 쓸 때 객체를 생성하고 @Bean 을 쓰면 스프링빈으로 등록됨
  // @Bean

  public PasswordEncoder passwordEncoder() {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder;
  }

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private AuthenticationFailureHandler authenticationFailureHandler;

  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  private AccessDeniedHandler accessDeniedHandler;


  // 원래는 DB 에서 사용자와 비밀번호를 받아와야하지만 지금은 메모리에 때려 박음
  // spring/1234 , admin/1234 라는 두 사용자를 스프링 시큐리티 인모메리에 때려 박아서 연습해보자
  // UserDetailsService 는 사용자 정보를 읽어오는 스프링 시큐리티 표준 인터페이스
    // ㄴ 원래는 사용자가 입력한 아이디로 DB 에서 사용자 정보(비밀번호, 권한 등) 를 읽어오는 클래스


  @Bean
  public UserDetailsService users() {
    // spring, admin 때려 박은 거
    UserDetails spring = User.builder().username("spring").password(encoder.encode("1234")).roles("USER").build();
    UserDetails admin = User.builder().username("admin").password(encoder.encode("1234")).roles("ADMIN").build();
    return new InMemoryUserDetailsManager(spring, admin);
  }

  // 1. Servlet : 자바 WAS(톰캣 등) 에서 실행 가능한 자바 클래스
  // class MyServlet implements HttpServlet { }

  // 2. 우리가 작성한 스프링 코드는 톰캣에서 어떻게 실행되지?
    //  ㄴ 우리는 대신 Python 의 Flask 로 웹 프로그래밍 기초를 했음
    //  ㄴ 스프링은 서블릿 뒤에서 실행됨 (DispatcherServlet)
    //  ㄴ DispatcherServlet 이 모든 사용자 요청을 접수해서 적절하게 컨트롤러를 불러준다
    //  ㄴ DispatcherServlet 은 호텔의 데스크, 은행의 대표번호에 해당한다
  // 3. 자바 웹에서 어떤 작업을 하기 전 또는 작업 후 어떤 코드를 실행하고 싶다면?
    //  ㄴ 예를 들어, 글 작성을 선택 → 로그인 여부 확인 → 비로그인이면 로그인 창을 띄운다 → 로그인 → 글 작성 페이지로 이동
    //  ㄴ Filter : DispatcherServlet 앞에서 실행 (즉, 스프링 기술이 아니고 자바 서블릿 표준 기술)
    //  ㄴ Interceptor :  컨트롤러 앞 또는 뒤에서 실행
    //  ㄴ AOP : 인터셉터보다 더 정밀하게 조건을 지정하여 발생 (예외가 발생하면, 리턴이 되면......)
  // extends 가 들어가면 옛날 설정이라 에러 (볼 줄 알아야함)
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // csrf 끄기
    http.csrf(a->a.disable());
    // /list 는 누구나 접근 가능, /write 는 USER 권한만 접근 가능, /admin 은 admin 권한만 접근 가능하게 할 수 있음
    // 스프링 시큐리티는 인증 (authentication. 대표적으로 로그인) 과 인가(authorization, 권한) 기능을 제공
      // ㄴ 인증은 기본적으로 formLogin 을 제공
          // ㄴ 로그인 화면을 보여주고 로그인 처리 (/login)
          // ㄴ formLogin 이 아닌 것도 있어? > JS로 로그인한다면? > 화면을 보여줄 수 없음 > 다른 로그인을 사용해야함
      // ㄴ 인가 : 어노테이션 base 인가를 사용하자 → @EnableMethodSecurity
    http.formLogin(c->c.successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler));
    // 로그인이 거부되면 아무 에러도 뜨지 않음
    // 전에 했던 파일에서 logback.xml 을 가져오고 로깅 레벨 지정을 debug 로 설정 후 서버 다시 실행
    http.logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/"));
                                                        // ㄴ 기본 주소를 쓰고 있기에 굳이 쓰지 않아도 되지만 일단 씀
    http.exceptionHandling(c->c.accessDeniedHandler(accessDeniedHandler));
    return http.build();
  }
}
