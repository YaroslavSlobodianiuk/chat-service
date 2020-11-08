package com.playtika;

import com.playtika.model.ChatUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChatUserTest {

    @Test
    public void createChaUserTest() {

        ChatUser user = new ChatUser();
        user.setUserName("oleg");
        user.setJoinTime(Timestamp.valueOf("2020-11-06 18:59:45.425000"));

        assertEquals("oleg", user.getUserName());
        assertEquals(Timestamp.valueOf("2020-11-06 18:59:45.425000"), user.getJoinTime());

    }

}
