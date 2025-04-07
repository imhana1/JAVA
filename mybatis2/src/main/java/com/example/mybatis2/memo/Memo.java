package com.example.mybatis2.memo;


import lombok.*;

import java.time.LocalDate;

// 스프링은 커맨드 객체를 기본 생성자로 생성한 다음, Setter 로 값을 집어 넣는다
// 롬복에서 @Builder 를 사용할 때 @AllArgsConstructor 도 필요
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Memo {
    private  int mno;
    private String title;
    private String content;
    private String writer;
    // 빌더를 사용한 경우 필드에 값을 직접 지정하는 인스턴스 초기화를 무시
    private LocalDate regDate;
}
