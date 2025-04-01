package com.example.demo.sample1;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

// if 예제

// Component는 이거 스프링빈으로 생성해~ 하는 의미만 가짐. 클래스에 대한 정보 전혀x
// Controller: 아 여긴 request 들어오고 모델앤뷰 나가고 그런거 하겠구나~ 하는걸 스프링이 알아
@Controller
public class SampleController {
  @GetMapping("/if/test1")
  public ModelAndView test1() {
    String username = null;
    return new ModelAndView("sample/test1").addObject("username", username );
  }

  @GetMapping("/if/test2")
  public ModelAndView test2() {
    Todo todo = new Todo(1, "집안 대청소하기", true);
    return new ModelAndView("sample/test2").addObject("todo", todo);
  }

  @GetMapping("/if/test3")
  public ModelAndView test3() {
    Sungjuck sungjuck = new Sungjuck("홍길동", 95);
    return new ModelAndView("sample/test3").addObject("sungjuck", sungjuck);
  }


}
