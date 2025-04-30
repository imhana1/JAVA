package com.example.demo05.controller;

import jakarta.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

// @ControllerAdvice 는 예외가 발생하면 동작하는 컨트롤러
@ControllerAdvice
public class SampleAdvice {
  @ExceptionHandler(ConstraintViolationException.class)
  public ModelAndView constraintViolationExceptionHandler(RedirectAttributes ra) {
    // RedirectAttributes 는 redirect 할 때 1회성 값을 가지고 이동하는 객체
    ra.addFlashAttribute("msg", "검증에 실패했습니다");
    return new ModelAndView("redirect:/");
  }
}
