package com.example.ws_back.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.format.*;

@Controller
public class WSController {
  // HTTP 컨트롤러는 클라이언트 → 서버 주소만 지정, 컨트롤러 마지막에 응답
  // 클라이언트 → 서버 = /pub
  // 서버 → 클라이언트 = /sub

  // 클라이언트에 메시지를 보내는 객체 → 전체 메시지 or 사용자를 지정해서
  @Autowired
  private SimpMessagingTemplate tpl;

  // /pub 가 생략된 발행하는 주소
  // 클라이언트가 /pub/job1 로 메시지를 보내면 실행된다
  // @MessageMapping("/job1")
  // 스프링 스케줄러 : 정해진 시간, 정해진 간격마다 실행하는 것
    // 10초마다 1번 씩 job1 을 실행해라
  @Scheduled(fixedDelay = 10000)
  public void job1() {
    LocalDateTime now = LocalDateTime.now();
    // 날짜를 문자열로 변환
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh시 mm분 ss초");
    String time = dtf.format(now);
    // /pub/job1 로 발행하면 /sub/job1 로 수신할 수 있다

    tpl.convertAndSend("/sub/job1", time);
  }

}
