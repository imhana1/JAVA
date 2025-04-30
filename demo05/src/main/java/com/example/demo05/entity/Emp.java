package com.example.demo05.entity;

// 사원 추가 : 이름, 이메일 필수 입력
// 사원 변경 : 번호, 이메일, 업무 필수입력
// 자바 빈 validation : 입력 값을 검증 → @NotEmpty, @NotNull


public class Emp {
  private int empno;
  private String name;
  private String email;
  private String job;
}
