package com.example.demo.sample2;

import com.example.demo.sample1.Todo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Sample2Controller {
    private List<Todo> todos = new ArrayList<>();

    @PostConstruct
    public void init() {
        todos.add(new Todo(1, "영어 공부", false));
        todos.add(new Todo(2, "수학 공부", true));
        todos.add(new Todo(3, "국어 공부", false));
    }

    @GetMapping("/for/list1")
    public ModelAndView list1() {
        return new ModelAndView("sample2/list1").addObject("todos", todos);
    }
}
