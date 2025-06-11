package com.example.demo6.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDto {
    @Data
    public static class Create {
        @NotNull
        private Integer pno;
        @NotEmpty
        private String content;
    }

    @Data
    public static class Delete {
        @NotNull
        private Integer cno;
        @NotNull
        private Integer pno;
    }
}
