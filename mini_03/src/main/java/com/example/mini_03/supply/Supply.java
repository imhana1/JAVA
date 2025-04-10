package com.example.mini_03.supply;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Supply {
  private Integer sno;
  private String name;
  private LocalDate regDate;
  private Integer quantity;
}
