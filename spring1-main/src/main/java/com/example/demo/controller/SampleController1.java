package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

// 컨트롤러 : 사용자 요청을 접수하고, 응답하는 함수들을 모아놓은 클래스
@RestController
public class SampleController1 {
  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }
}
