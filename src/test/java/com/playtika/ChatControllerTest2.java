package com.playtika;

import com.playtika.controller.ChatController;
import com.playtika.model.ChatMessage;
import com.playtika.model.ChatUser;
import com.playtika.repository.ChatMessageRepository;
import com.playtika.repository.ChatUserRepository;
import com.playtika.service.ChatMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ChatServiceApplication.class
)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ChatControllerTest2 {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ChatController chatController;

    @Autowired
    ChatMessageService messageService;

    @MockBean
    ChatUserRepository userRepository;

    @MockBean
    ChatMessageRepository messageRepository;


    @Test
    public void getMessagesIntEmptyListTest() throws Exception {

        this.mockMvc.perform(post("/getMessages")
                .accept(MediaType.APPLICATION_JSON)
                .param("userName", "oleg")
                .param("data", "hi"))
                .andDo(print()).andReturn();

    }

    @Test
    public void getMessagesIntTest() throws Exception {
        ChatUser user = new ChatUser("oleg", Timestamp.valueOf("2020-11-06 18:59:45.425000"));
        Optional<ChatUser> optionalChatUser = Optional.of(user);

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(optionalChatUser);

        this.mockMvc.perform(post("/getMessages")
                .accept(MediaType.APPLICATION_JSON)
                .param("userName", "oleg")
                .param("data", "hi"))
                .andDo(print()).andReturn();

    }
}
