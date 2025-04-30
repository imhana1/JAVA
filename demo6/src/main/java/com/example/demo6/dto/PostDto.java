package com.example.demo6.dto;

import com.example.demo6.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

// PostDto 는 Dto 들을 담는 클래스 -> Dto 클래스 개수 줄여 PostDto.Pages, PostDto.Create
public class PostDto {
    // 페이징 출력 DTO
    @Data
    @AllArgsConstructor
    public static  class Pages{
        private int prev;
        private int start;
        private int end;
        private int next;
        private int pageno;
        private List<Post> posts;
    }

    // 글을 작성하는 DTO
    public static class Create {

    }

    // 기타 DTO
}
