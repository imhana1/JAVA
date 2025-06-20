package com.example.ws_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class WSController {
    // 클라이언트에 메시지를 보내는 객체 -> 전체 메시지 or 사용자를 지정해서
    @Autowired
    private SimpMessagingTemplate tpl;
    // HTTP 컨트롤러는 클라이언트 -> 서버 주소만 지정, 컨트롤러 마지막에 응답
    // /pub가 생략된 발행하는 주소. 클라이언트가 /pub/job1로 메시지를 보내면 실행된다
    
    // 스프링 스케줄러 : fixedDelay 또는 cron 식(유닉스 스케줄링 표현식) -> cron maker에서 작성
    // 1분에 한번씩 실행. 복사할 때 마지막은 빼고 복사
//    @Scheduled(fixedDelay = 10000)
    public void job1() {
        LocalDateTime now = LocalDateTime.now();
        // 날짜를 문자열로 변환
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh시mm분ss초");
        String time = dtf.format(now);

        // /sub/job1로 수신할 수 있다
        tpl.convertAndSend("/sub/job1", time);
    }

    @MessageMapping("/job2")
    public void job2(String message, Principal principal) {
        String username = principal==null? "GUEST" : principal.getName();
        tpl.convertAndSend("/sub/job2", username + ": " + message);
    }

    // 글을 작성하면 작성되었다고 웹소켓 메시지를 보내자
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/message")
    public ResponseEntity<Void> job3(String receiver, String message) {
        String sender = "hana";
        if(sender.equals(receiver))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        // receiver에게만 웹소켓 메시지를 전송
        // /sub/job3를 수신주소로 특정 사용자에게 메시지를 보낸다 -> /user/sub/job3로 수신

        System.out.println(receiver);
        tpl.convertAndSendToUser(receiver, "/sub/job3", sender + "메시지");
        return ResponseEntity.ok(null);
    }
    // 클라이언트 -> 서버 /pub
    // 서버 -> 클라이언트
}
