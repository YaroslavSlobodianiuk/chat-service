package com.playtika;

import com.playtika.model.ChatMessage;
import com.playtika.service.ChatUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class WebSocketTest {

    @MockBean
    ChatUserService userService;

    @LocalServerPort
    private Integer port;

    private WebSocketStompClient webSocketStompClient;

    @Before
    public void setup() {
        this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(
                List.of(new WebSocketTransport(new StandardWebSocketClient()))));
    }

    @Test
    public void messageMappingSendMessageTest() throws InterruptedException, ExecutionException, TimeoutException {

        BlockingQueue<ChatMessage> blockingQueue = new ArrayBlockingQueue(1);
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setSender("yarik");
        chatMessage.setType(ChatMessage.MessageType.JOIN);
        chatMessage.setTime(Timestamp.valueOf(LocalDateTime.now()));

        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession session = webSocketStompClient
                .connect(String.format("ws://localhost:%d/ws", port), new StompSessionHandlerAdapter() {
                })
                .get(1, SECONDS);

        session.subscribe("/topic/public", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return ChatMessage.class;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object payload) {
                System.out.println("Received message: " + payload);
                blockingQueue.add((ChatMessage) payload);
            }
        });

        session.send("/app/chat.sendMessage", chatMessage);

        assertEquals(chatMessage.getSender(), blockingQueue.poll(1, SECONDS).getSender());
    }

    @Test
    public void messageMappingAddUserTest() throws InterruptedException, ExecutionException, TimeoutException {

        BlockingQueue<ChatMessage> blockingQueue = new ArrayBlockingQueue(1);
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setSender("yarik");
        chatMessage.setType(ChatMessage.MessageType.JOIN);
        chatMessage.setTime(Timestamp.valueOf(LocalDateTime.now()));

        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession session = webSocketStompClient
                .connect(String.format("ws://localhost:%d/ws", port), new StompSessionHandlerAdapter() {
                })
                .get(1, SECONDS);

        session.subscribe("/topic/public", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return ChatMessage.class;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object payload) {
                System.out.println("Received message: " + payload);
                blockingQueue.add((ChatMessage) payload);
            }
        });

        Mockito.doNothing().when(userService).deleteUser(Mockito.anyString());

        session.send("/app/chat.addUser", chatMessage);

        assertEquals(chatMessage.getSender(), blockingQueue.poll(1, SECONDS).getSender());
    }

}
