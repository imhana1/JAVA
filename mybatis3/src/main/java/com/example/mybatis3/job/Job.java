package com.example.mybatis3.job;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Job {
    private int jno;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;
}
