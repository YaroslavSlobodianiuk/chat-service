package com.playtika;

import com.playtika.model.ChatMessage;
import com.playtika.service.ChatMessageService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ChatMessageService messageService = new ChatMessageService();
        List<ChatMessage> messages = messageService.getMessagesByEnteredData("oleg", "hi");
        for (ChatMessage chatMessage : messages) {
            System.out.println(chatMessage);
        }

    }
}
