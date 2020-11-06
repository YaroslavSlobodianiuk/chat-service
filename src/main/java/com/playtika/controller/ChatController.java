package com.playtika.controller;

import com.playtika.model.ChatMessage;
import com.playtika.repository.ChatMessageRepository;
import com.playtika.service.ChatMessageService;
import com.playtika.service.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatUserService userService;
    @Autowired
    private ChatMessageService messageService;


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        messageService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        userService.addUser(chatMessage.getSender(), chatMessage.getTime());

        return chatMessage;
    }

    @PostMapping("/getMessage")
    public List<ChatMessage> getMessagesByEnteredData(@RequestParam String userName, @RequestParam String data) {
        return messageService.getMessagesByEnteredData(userName, data);
    }

}