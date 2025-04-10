package com.example.mini_03.todo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TodoService {
  @Autowired
  private TodoDao todoDao;

  public int save(Todo todo) {
    todoDao.save(todo);
    return todo.getTno();
  }

  public List<Todo>findAll() {
    return todoDao.findAll();
  }

  public Todo findByTno(Integer tno) {
    return todoDao.findByTno(tno);
  }

  public void finish(Integer tno) {
    todoDao.finish(tno);
  }

  public void delete(Integer tno) {
    todoDao.delete(tno);
  }
}
