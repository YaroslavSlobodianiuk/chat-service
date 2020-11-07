package com.playtika;

import com.playtika.model.ChatUser;
import com.playtika.repository.ChatUserRepository;
import com.playtika.service.ChatUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ChatServiceApplication.class
)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ChatUserServiceTest {

    @Autowired
    ChatUserService userService;

    @Test
    public void addAndRemoveUserTest() {
        userService.addUser("oleg", Timestamp.valueOf("2020-11-06 18:59:45.425000"));
        userService.deleteUser("oleg");
    }
}
