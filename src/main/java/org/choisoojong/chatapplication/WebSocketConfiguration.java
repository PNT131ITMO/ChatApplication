package org.choisoojong.chatapplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
/*
Đóng vai trò cấu hình trong WebSocket và STOMP
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // /topic : route that websocket broadcasts user's message
        config.setApplicationDestinationPrefixes("/app"); // /app : prefix for message going to controller
    }

    /*
    STOMP : Simple Text Oriented Messaging Protocol : the protocol that the
            websocket will follow , making it easier for different clients to communicate with it
    "/chat"  : the URL used to connect to websocket
    SockJS : Library for browsers that don't support websocket , a backup plan to ensure everyone can connect
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
}
