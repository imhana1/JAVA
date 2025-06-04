package com.example.demo6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {
    // 프론트에서 현재 로그인 상태를 물어오면 응답
    // 로그인한 경우 : 200 + 로그인 아이디
    // 비로그인 : 409 + null
    @GetMapping(path = "/api/auth/check")
    public ResponseEntity<Map<String, String>> checkLogin(Principal principal) {
        if (principal != null)
            return ResponseEntity.ok(Map.of("username", principal.getName()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
}
