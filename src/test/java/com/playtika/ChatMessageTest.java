package com.playtika;

import com.playtika.model.ChatMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ChatServiceApplication.class
)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ChatMessageTest {

    @Test
    public void createChatMessageTest() {
        ChatMessage message = new ChatMessage(3L, ChatMessage.MessageType.CHAT, "hi", "oleg", Timestamp.valueOf("2020-11-06 18:59:45.425000"));

        assertEquals(java.util.Optional.of(3L), Optional.of(message.getId()));
        assertEquals(ChatMessage.MessageType.CHAT, message.getType());
        assertEquals("hi", message.getContent());
        assertEquals("oleg", message.getSender());
        assertEquals(Timestamp.valueOf("2020-11-06 18:59:45.425000"), message.getTime());


    }


}
