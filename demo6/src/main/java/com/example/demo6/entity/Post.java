package com.example.demo6.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
  private int pno;
  private String title;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String content;
  private String writer;
  @Builder.Default
  @JsonFormat(pattern = "yyyy년 MM월 dd일 hh:mm:ss")
  private LocalDateTime writeTime = LocalDateTime.now();
  @Builder.Default
  private int readCnt = 0;
  @Builder.Default
  private int goodCnt = 0;
  @Builder.Default
  private int badCnt = 0;


}
