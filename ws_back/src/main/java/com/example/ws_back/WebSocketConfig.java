package com.example.ws_back;

// STOMP 웹소켓 설정

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// 스프링 설정 파일
@Configuration
// STOMP기반(텍스트 메시징 전용)
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트는 SockJS를 이용해 /ws로 접속을 하게 된다
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 메시지를 수신하는 주소는 /sub로 시작한다
        registry.enableSimpleBroker("/sub");

        // 클라이언트는 메시지를 보내려면 /pub로 시작한다
        registry.setApplicationDestinationPrefixes("/pub");

        // A와 B가 채팅을 한다면
        // 각자 채팅 메시지를 서버로 보내야한다(발행) : /pub/chat1
        // 서버가 보내주는 채팅 메시지를 수신해야 한다(구독) : /sub/chat1
    }
}
