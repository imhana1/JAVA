package com.example.board_2.board2;

import lombok.*;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Board {
    private Integer bno;
    private String title;
    private String content;
    private String nickname;
    private String password;
    private LocalDateTime writeTime;
    private Integer readCnt;
}
