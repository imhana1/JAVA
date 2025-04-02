package com.example.demo3.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Todo {
    private int tno;
    private String title;
    private final LocalDate regDate = LocalDate.now();
    private final boolean finish = false;
}
