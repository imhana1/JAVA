package com.example.demo6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestLoginController {
  @GetMapping("/login")
  public void login() {
  }
}
