package com.playtika;

import com.playtika.controller.ChatController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatServiceApplicationTests {

    @Autowired
    private ChatController chatController;

    @Test
    void contextLoads() {
        assertThat(chatController).isNotNull();
    }

}
