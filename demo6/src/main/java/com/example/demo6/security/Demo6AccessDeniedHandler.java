package com.example.demo6.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 403(권한 없음)에 대응 -> html 로 403오류화면이 뜸 -> ResponseEntity 로 바꾸자 -> 여기는 리턴이 void 임,,?
@Component
public class Demo6AccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 스프링은 json 출력 기능이 없다 -> 외부 라이브러리를 사용
        // 스프링의 기본 json 라이브러리는 jackson
        // rest 방식은 데이터 + 상태코드로 응답 -> 스프링이라면 ResponseEntity 를 리턴하면 알아서 json 으로 바꿔서 출력
        // 스프링 시큐리티의 핸들러들은 사실 자바 코드 -> 내가 수동으로 json 변환을 해야 한다
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(403);

        // response 로 응답하면 한글 출력에 문제가 있다
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(mapper.writeValueAsString("작업 권한이 없습니다."));
    }
}
