package com.example.demo3.supply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// 비품 번호, 입고일, 이름, 개수
// 사용자로 부터 값을 입력받는 객체 (Command 객체)
// 스프링에서 커맨드 객체는 반드시 기본 생성자와 Setter 가 있어야 한다
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supply {
    private Integer sno;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;
    private Integer quantity;
}
