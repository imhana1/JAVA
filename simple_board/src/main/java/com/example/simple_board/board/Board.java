package com.example.simple_board.board;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Board {
    private int bno;
    private String title;
    private String content;
    private String nickname;
    private String password;
    private LocalDateTime writeTime;
    private Integer readCnt;

}
