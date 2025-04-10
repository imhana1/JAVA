package com.example.mini_03;

import com.example.mini_03.todo.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoServiceTest {
  @Autowired
  private TodoService todoService;
  private Object assertEquals;

  @Test
  public void saveTest() {
    Todo todo = new Todo(null, "연습", "연습의 연습", null, LocalDate.of(2025,4,9), null);
    System.out.println(todo);

    todoService.save(todo);
    System.out.println(todo);
  }
}
