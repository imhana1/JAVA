package com.example.demo.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.time.*;

@Controller
public class SampleController3 {
  // 숫자를 입력받아 제곱해서 출력 : 3을 입력하면 9를 출력
  @GetMapping("/square/input")
  public ModelAndView squareInput() {
    return new ModelAndView("square/intput");
  }

  @PostMapping("/square/output")
  public ModelAndView squareOutput(int a) {
    int result = a * a;
    return new ModelAndView("square/output").addObject("result", result);
  }

  // 이름과 태어난 년도를 입력하면 "홍길동님은 15살" 출력
  @GetMapping("/age")
  public ModelAndView age() {
    return new ModelAndView("age/input");
  }

  @PostMapping("/age")
  public ModelAndView age(String name, int birthYear) {
    int thisYear = LocalDate.now().getYear();
    int age = thisYear - birthYear;
    // 백에서 출력 문자열 작업하고, 타임리프는 바로 출력만 담당
    String message = name + "님은 " + age + "살입니다";
    return new ModelAndView("age/output").addObject("message", message);
  }

  @GetMapping("/add")
  public ModelAndView add() {
    return new ModelAndView("add/input");
  }

  @PostMapping("/add")
  public ModelAndView add(int a, int b) {
    int result = a + b;
    String message = a + " + " + b + " = " + result;
    return new ModelAndView("add/output").addObject("message", message);
  }
}















