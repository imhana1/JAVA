package com.example.demo6;

import com.example.demo6.execise.*;
import com.example.demo6.execise.EmpDao2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmpDao2Test {
  @Autowired
  private EmpDao2 empDao;

  @Test
  public void findByDeptno() {
    System.out.println(empDao.findByDeptno(10));
  }
}
