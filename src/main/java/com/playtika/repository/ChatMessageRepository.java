package com.playtika.repository;

import com.playtika.model.ChatMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;


public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {

    @Query("select content from user_messages where sender= ?1 and content = ?2 and time >= ?3")
    List<ChatMessage> findMessageByData(String user, String data, Timestamp time);

}