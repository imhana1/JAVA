package com.example.mini_03.todo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class TodoController {
  @Autowired
  private TodoService todoService;

  @GetMapping("/todo/list")
  public ModelAndView findAll() {
    return new ModelAndView("todo/list").addObject("todos", todoService.findAll());
  }

  @GetMapping("/todo/add")
  public ModelAndView save() {
    return new ModelAndView("todo/add");
  }

  @PostMapping("/todo/add")
  public ModelAndView save(@ModelAttribute Todo todo) {
    int tno = todoService.save(todo);
    return new ModelAndView("redirect:/todo/read?tno=" + tno);
  }

  @GetMapping("/todo/read")
  public ModelAndView findById(@RequestParam Integer tno) {
    Todo todo = todoService.findByTno(tno);
    if(todo==null)
      return new ModelAndView("redirect:/todo/list");
    return new ModelAndView("todo/read").addObject("todo", todo);
  }

  @PostMapping("/todo/finish")
  public ModelAndView finish(@RequestParam Integer tno) {
    todoService.finish(tno);
    return new ModelAndView("redirect:/todo/read?tno=" + tno);
  }

  @PostMapping("/todo/delete")
  public ModelAndView delete(@RequestParam Integer tno) {
    todoService.delete(tno);
    return new ModelAndView("redirect:/todo/list");
  }
}
