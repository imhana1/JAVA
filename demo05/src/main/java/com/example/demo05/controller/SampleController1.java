package com.example.demo05.controller;

import org.springframework.security.access.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class SampleController1 {
  @GetMapping("/everyone")
  public ModelAndView 누구나_접근가능() {
    return new ModelAndView("index");
  }

  @PreAuthorize("isAnonymous()")
  @GetMapping("/non_auth")
  public void 비로그인만_접근가능() {

  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/auth")
  public void 로그인해야_접근가능() {

  }

  @Secured("ROLE_USER")
  @GetMapping("/user")
  public void USER권한만_접근가능() {
    // 권한은 중복되지 않음 하나하나 권한을 줘야함

  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/admin")
  public void ADMIN권한만_접근가능() {

  }
}
