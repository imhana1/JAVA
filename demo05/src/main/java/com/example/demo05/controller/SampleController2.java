package com.example.demo05.controller;

import com.example.demo05.dto.*;
import jakarta.validation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

// 스프링 빈 검증 활성화
// 보통 스프링 어쩌구는 자바 표준 어쩌구의 기능 확장 버전인 경우가 많다

@Validated
@Controller
public class SampleController2 {

  // 주의할 점: @Valid 바로 뒤에 BindingResult 가 따라와야한다
  //          검증을 수행해서 실패하면 오류 메시지를 바로 뒤에 있는 BindingResult 에 담는 형태
  // ※ 스프링 컨트롤러 메소드의 파라미터는 정해진 순서나 타입이 없다 (스프링이 알아서 주입해준다)
  // 스프링에 있는 모든 객체 중 내가 원하는 걸 적으면 알아서 자동으로 주입해준다
  // @Valid : 검증하라는 뜻으로 @Valid EmpCreateDto dto 는 dto 를 검증한 후 또 검증하라는 뜻. 그래서 BindingResult 가 무조건 뒤에 와야함
  // 스프링 검증 스타일
  @GetMapping("/test1")
  public ModelAndView empTest1(@Valid EmpCreateDto dto, BindingResult br) {
    System.out.println(dto);
    return null;
  }
}
