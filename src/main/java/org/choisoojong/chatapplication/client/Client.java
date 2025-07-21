package org.choisoojong.chatapplication.client;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

public class Client {
    // Stomp session allows to connect to stomp servers (our websocket)
    private StompSession session;
    private String username;

    public Client(String username) {
        this.username = username;

        List<Transport> transports = new ArrayList<Transport>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        // Convert a Java object(Message) to JSON form
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }


}
