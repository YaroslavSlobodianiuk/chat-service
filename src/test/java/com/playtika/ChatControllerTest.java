package com.playtika;

import com.playtika.controller.ChatController;
import com.playtika.model.ChatMessage;
import com.playtika.model.ChatUser;
import com.playtika.repository.ChatMessageRepository;
import com.playtika.repository.ChatUserRepository;
import com.playtika.service.ChatMessageService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ChatServiceApplication.class
)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ChatControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ChatController chatController;

    @MockBean
    ChatMessageService messageService;

    @MockBean
    ChatUserRepository userRepository;

    @MockBean
    ChatMessageRepository messageRepository;

    @Test
    public void getMessagesIntTest() throws Exception {


        this.mockMvc.perform(post("/getMessages")
                .accept(MediaType.APPLICATION_JSON)
                .param("userName", "oleg")
                .param("data", "hi"))
                .andDo(print()).andReturn();

    }

    @Ignore
    @Test
    public void getMessagesEndpointTest() throws Exception {

        this.mockMvc.perform(post("/getMessages")
            .accept(MediaType.APPLICATION_JSON)
            .param("userName", "oleg")
            .param("data", "hi"))
            .andDo(print()).andReturn();


        Mockito.verify(messageService).getMessagesByEnteredData(Mockito.anyString(), Mockito.anyString());

//        ChatMessage message = new ChatMessage(3L, ChatMessage.MessageType.CHAT, "hi", "oleg", Timestamp.valueOf("2020-11-06 18:59:45.425000"));
//        List<ChatMessage> expectedMessages = new ArrayList<>();
//        expectedMessages.add(message);
//        List<ChatMessage> messageList = chatController.getMessagesByEnteredData("oleg", "hi");
//
//        assertEquals(expectedMessages, messageList);


//        RequestBuilder request = MockMvcRequestBuilders.post("/getMessages").param("userName", "oleg").param("data", "hi");
//        MvcResult result = mockMvc.perform(request).andReturn();
//        System.out.println(result.getResponse());

//        this.mockMvc.perform(post("/getMessages")
//                .param("userName", "oleg")
//                .param("data", "hi"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(content().string(""));
    }

}
