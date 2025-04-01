package com.example.demo.controller;

import jakarta.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class SampleController4 {
  private List<Integer> numbers = new ArrayList<>();
  @PostConstruct
  private void init() {
    numbers.add(10);     numbers.add(20);        numbers.add(30);
  }

  @GetMapping("/numbers/list")
  public ModelAndView list() {
    int hap = 0;
    for(int i:numbers) {
      hap += i;
    }
    double avg = (double)hap/numbers.size();
    return new ModelAndView("numbers/list").addObject("hap", hap)
        .addObject("avg", avg);
  }

  @PostMapping("/numbers/add")
  public ModelAndView add(int num) {
    numbers.add(num);
    return new ModelAndView("redirect:/numbers/list");
  }
}







