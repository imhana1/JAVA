package com.example.demo6.controller;

import com.example.demo6.dto.CommentDto;
import com.example.demo6.entity.Comment;
import com.example.demo6.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Validated
@RestController
public class CommentController {
    // 댓글 CRUD : Create, Delete 담당 Read는 PostController에서, Update는 없다
    @Autowired
    private CommentService service;

    // @RequestBody
    // @ResponseBody + HTTP 상태 코드 => ResponseEntity
    // 인증 (authentication) : 신원을 확인 (로그인) -> 401
    //      http는 상호 인증한다 -> 브라우저가 서버의 공인 인증서를 확인
    // 인가 (authorization) : 인증 후 권환을 확인 -> 403
    @Operation(summary = "댓글 작성", description = "댓글 작성 후 글의 모든 댓글 리턴")
    @Secured("ROLE_USER")
    @PostMapping("/api/comments/new")
    public ResponseEntity<List<Comment>> write(@ModelAttribute @Valid CommentDto.Create dto, BindingResult br, Principal principal) {
        // PostRead <- Comments, CommentWrite. CommentWrite 에서 댓글을 작성하면 Comments를 갱신
        List<Comment> comments = service.write(dto, principal.getName());
        return ResponseEntity.ok(comments);

    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제 후 글의 모든 댓글 리턴")
    @Secured("ROLE_USER")
    @DeleteMapping("/api/comments")
    public ResponseEntity<List<Comment>> delete(@ModelAttribute @Valid CommentDto.Delete dto, BindingResult br, Principal principal) {
        List<Comment> comments = service.delete(dto, principal.getName());
        return ResponseEntity.ok(comments);
    }
}











