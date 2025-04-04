package com.example.mybatis1.todo;

import lombok.*;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
  private int tno;
  private String title;
  private LocalDate regDate = LocalDate.now();
  private boolean finish = false;
}
