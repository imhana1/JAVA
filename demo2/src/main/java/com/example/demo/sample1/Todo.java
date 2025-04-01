package com.example.demo.sample1;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString  // 개발자가 값 보려는 어노테이션이지 배웠지
public class Todo {
  private int tno;
  private String title;
  private boolean finish;
}
