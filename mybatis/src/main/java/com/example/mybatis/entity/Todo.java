package com.example.mybatis.entity;

// Value Object : 값을 저장한 클래스
// Entity : DB 테이블과 동일하게 작성한 클래스

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Todo {
    private int tno;
    private String title;
    private LocalDate regDate;
    private boolean finish;
}
