package com.example.demo3.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController2 {
    private List<Todo> todos = new ArrayList<>();
    private int tno = 1;

    // @GetMapping ("todo/list") - 주소가 같아서 실행이 안됨
    public ModelAndView list() {
        return new ModelAndView("todo/list").addObject("todos", todos);
    }

    public ModelAndView write(@RequestParam String title) {
        Todo newTodo = new Todo(tno ++, title);
        todos.add(newTodo);
        return new ModelAndView("redirect:/todo/list");
    }

    public ModelAndView finish(@RequestParam int tno) {
        for (Todo todo:todos){
            if (todo.getTno() == tno) {
                todo.setFinish(true);
                break;
            }
        }
        return new ModelAndView("redirect:/todo/list");
    }

    public ModelAndView delete(@RequestParam int tno) {
        for (int i = todos.size() - 1; i >= 0; i --) {
            if (todos.get(i).getTno() == tno) {
                todos.remove(i);
                break;
            }
        }
        return new ModelAndView("redirect:/todo/list");
    }
}
