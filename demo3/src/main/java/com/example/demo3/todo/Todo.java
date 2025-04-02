package com.example.demo3.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class Todo {
    private int tno;
    private String title;
    private final LocalDate regDate = LocalDate.now();
    @Setter
    private boolean finish = false;
    public Todo(int tno, String title) {
        this.tno = tno;
        this.title = title;
    }
}
