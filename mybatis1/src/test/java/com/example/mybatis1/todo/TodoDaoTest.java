package com.example.mybatis1.todo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.websocket.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoDaoTest {
  @Autowired
  private TodoDao todoDao;

  //@Test
  public void init() {
    assertNotNull(todoDao);
  }

  //@Test
  public void saveTest() {
    Todo todo = new Todo(0, "치킨", LocalDate.of(2025,4,5), false);
    int result = todoDao.save(todo);
    assertEquals(1, result);
  }

  //@Test
  public void finishTest() {
    int result = todoDao.finish(1);
    assertEquals(1, result);
  }

  @Transactional
  @Test
  public void deleteTest() {
    int result = todoDao.delete(1);
    assertEquals(1, result);
  }
}
