package com.example.demo.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class SampleController2 {
  // /test1로 들어오면 타임리프 test1.html로 보내는 컨트롤러 메소드
  // Model : 데이터, View : 화면
  // 컨트롤러는 Model과 View를 연결하고 제어 -> 그래서 이름이 Controller
  // 자바 웹은 Model, View, Controller로 구성 -> MVC 패턴
  @GetMapping("/test1")
  public ModelAndView test1() {
    return new ModelAndView("test1");
  }

  @GetMapping("/test2")
  public ModelAndView test2() {
    return new ModelAndView("test2");
  }

  // message라는 이름의 Model을 test3 뷰로 보낸다
  @GetMapping("/test3")
  public ModelAndView test3() {
    return new ModelAndView("test3").addObject("message", "Hello World");
  }

  // 두개의 정수를 test4뷰로 보낸다
  // test4 뷰는 두 정수를 더해서 출력한다
  @GetMapping("/test4")
  public ModelAndView test4() {
    int a = 10;
    int b = 20;
    return new ModelAndView("test4").addObject("a", a).addObject("b", b);
  }
}

