package com.example.demo6.execise;

import lombok.Data;

import java.util.List;

@Data
public class DeptWithEmp {
  private int deptno;
  private String dname;
  private String loc;
  private List<Emp> emps;
}
