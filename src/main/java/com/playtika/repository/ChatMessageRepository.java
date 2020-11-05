package com.playtika.repository;

import com.playtika.model.ChatMessage;
import com.playtika.model.MessageStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//public interface ChatMessageRepository
//        extends CrudRepository<ChatMessage, String> {
//
//    long countBySenderIdAndRecipientIdAndStatus(
//            String senderId, String recipientId, MessageStatus status);
//
//    List<ChatMessage> findByChatId(String chatId);
//}