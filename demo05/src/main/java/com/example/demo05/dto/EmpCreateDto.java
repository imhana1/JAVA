package com.example.demo05.dto;

import jakarta.validation.constraints.*;
import lombok.*;

// DTO : Data Transfer Object
  //  사용자에게 받아오고, 사용자에게 출력하는 클래스
  //  화면 모양과 저장된 데이터는 보통 불일치

// setter + getter + toString
@Data
public class EmpCreateDto {
  @NotEmpty
  private String name;
  @NotEmpty
  private String email;
}
