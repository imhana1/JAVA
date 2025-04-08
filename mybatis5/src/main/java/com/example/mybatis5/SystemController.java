package com.example.mybatis5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController {
    @GetMapping("/")
    public ModelAndView root() {
        return new ModelAndView("index");

    }
}
