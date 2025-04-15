package com.example.board_2.board2;

import lombok.*;
import org.apache.ibatis.annotations.*;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Comment {
    private Integer cno;
    private String content;
    private String nickname;
    private String password;
    private LocalDateTime writeTime;
    private Integer bno;
}