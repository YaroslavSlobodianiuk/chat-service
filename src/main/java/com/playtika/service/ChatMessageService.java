package com.playtika.service;

import com.playtika.exception.SearchMessagesTransactionException;
import com.playtika.model.ChatMessage;
import com.playtika.model.ChatUser;
import com.playtika.repository.ChatMessageRepository;
import com.playtika.repository.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository messageRepository;
    @Autowired
    private ChatUserRepository userRepository;

    public void saveMessage(ChatMessage chatMessage) {
        messageRepository.save(chatMessage);
    }

    @Transactional(rollbackFor = SearchMessagesTransactionException.class)
    public List<ChatMessage> getMessagesByEnteredData(String userName, String data) {
        Optional<ChatUser> user = userRepository.findById(userName);
        if (!user.isPresent()) {
            return Collections.emptyList();
        }
        return messageRepository.findMessageByData(userName, data, user.get().getJoinTime());
    }
}
