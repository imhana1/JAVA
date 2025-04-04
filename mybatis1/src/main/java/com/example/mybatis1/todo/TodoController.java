package com.example.mybatis1.todo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class TodoController {
  @Autowired
  private TodoDao todoDao;

  @GetMapping("/todo/list")
  public ModelAndView findAll() {
    return new ModelAndView("todo/list").addObject("todos", todoDao.findAll());
  }

  @PostMapping("/todo/write")
  public ModelAndView save(@ModelAttribute Todo todo) {
    todoDao.save(todo);
    return new ModelAndView("redirect:/todo/list");
  }

  @PostMapping("/todo/finish")
  public ModelAndView finish(@RequestParam Integer tno) {
    todoDao.finish(tno);
    return new ModelAndView("redirect:/todo/list");
  }

  @PostMapping("/todo/delete")
  public ModelAndView delete(@RequestParam Integer tno) {
    todoDao.delete(tno);
    return new ModelAndView("redirect:/todo/list");
  }
}
