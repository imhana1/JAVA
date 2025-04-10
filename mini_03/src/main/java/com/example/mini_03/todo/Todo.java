package com.example.mini_03.todo;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Todo {
  private Integer tno;
  private String title;
  private String memo;
  private LocalDate regDate;
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDate deadline;
  private Boolean finish;
}
