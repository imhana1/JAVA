package com.example.demo6.controller;

import com.example.demo6.dto.PostDto;
import com.example.demo6.entity.Post;
import com.example.demo6.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Validated
@RestController
public class PostController {
    @Autowired
    private PostService service;

    @Operation(summary = "페이징", description = "기본 페이지번호 1, 페이지 크기 10으로 페이징")
    @GetMapping("/posts")
    public ResponseEntity<PostDto.Pages> findAll(@RequestParam(defaultValue = "1") int pageno,
                                                 @RequestParam(defaultValue = "10") int pagesize) {
        return ResponseEntity.ok(service.findAll(pageno, pagesize));
    }

    // @RequestParam 이나 @ModelAttribute 에서는 사용자가 값을 넘기지 않으면 null 이 되고 null 체크로 오류 발견
    // @PathVarialble 은 사용자가 넘기는 값이 주소의 일부
    // - pno 가 111이라면 get  /posts/111, 그런데 값을 안넘겼다면 get  /posts.  즉, 주소가 아예 다르다
    @Operation(summary = "글 읽기", description = "글 읽기")
    @GetMapping("/posts/post")
    public ResponseEntity<Post> findByPno(@RequestParam int pno, Principal principal) {
        // 로그인했으면 로그인 아이디, 비로그인이면 null 을 대입
        String loginId = principal == null ? null : principal.getName();
        return ResponseEntity.ok(service.findByPno(pno, loginId));

        // MemberController : 컨트롤러에서 if 문 걸어서 200 응답, 또는 409 응답을 만들어냄

        // 또 다른 방법으로는 내가 원하지 않는 방향으로 진행되면 서비스에서 예외 발생 -> ControllerAdvice 로 넘긴다
        // 컨트롤러는 200응답(바람직한 흐름)만 담당, 바람직하진 않은 흐름은 어드바이스에서 담당
    }

}

















