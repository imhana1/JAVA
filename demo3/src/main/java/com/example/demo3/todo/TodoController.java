package com.example.demo3.todo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {
    private List<Todo> todos = new ArrayList<>();
    private int tno = 1;

    // C - GET, POST
    @GetMapping("/todo/write")
    public void write() {

    }
    // <input nate='title'>이 요청 객체에 담겨 백엔드로 전달되면 같은 이름의 파라미터에 담는다
    // @RequestParam : 요청 객체에서 이름이 같은 데이터를 추출해 파라미터에 담아라
    //                 스프링은 그 어떤 데이터라도 파라미터로 변환할 수 있을까?
    //                 <input type = 'date'> -> 2020-11-20 -> 2020년 11월 20일 객체로 변환이 될까? 안됨.
    @PostMapping("/todo/write")
    public ModelAndView write(@RequestParam String title) {
        Todo todo = new Todo(tno++, title);
        todos.add(todo);
        return new ModelAndView("redirect:/todo/list");
    }

    // R - 목록
    @GetMapping("/todo/list")
    public ModelAndView list(){
        return new ModelAndView("/todo/list").addObject("todos", todos);
    }

}
