package com.example.ws_back.controller;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

// 웹소켓 연결, 해제에 대한 스프링 이벤트 핸들러 등록
@Component
public class WebSocketEventListener {
    @EventListener
    public void connect(SessionConnectedEvent e) {
        // 웹소켓 연결이 생성된 이벤트
        System.out.println("WebSocket 연결 : " + e.getMessage());
    }

    @EventListener
    public void disconnect(SessionConnectedEvent e) {
        // 웹소켓 연결이 끊어진 이벤트
        System.out.println("WebSocket 연결종료 : " + e.getMessage());
    }
}
