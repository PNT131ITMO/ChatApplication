package org.choisoojong.chatapplication.client;

import org.choisoojong.chatapplication.Message;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class StompSessionHandler extends StompSessionHandlerAdapter {
    private String username;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/messages", new StompSessionHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                try {
                    if (payload instanceof Message) {
                        Message message = (Message) payload;
                        System.out.println("Received message from user : " + message.getUser() + ": " + message.getMessage());
                    } else {
                        System.out.println("Received message from user : " + payload.getClass());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
