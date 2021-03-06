package com.playtika.service;

import com.playtika.model.ChatUser;
import com.playtika.repository.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Service
public class ChatUserService {

    @Autowired
    private ChatUserRepository userRepository;

    public void addUser(String userName, Timestamp time) {
        ChatUser user = new ChatUser(userName, time);
        userRepository.save(user);
    }
    public void deleteUser(@RequestParam String username) {
        userRepository.deleteById(username);
    }
}
