package org.choisoojong.chatapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/message")
    public void handleMessage(Message message) {
        System.out.println("Received message from user : " + message.getUser() + ": " + message.getMessage());
        template.convertAndSend("/topic/message", message);
        System.out.println("Sent message: " + message.getUser() + ": " + message.getMessage());
    }
}
