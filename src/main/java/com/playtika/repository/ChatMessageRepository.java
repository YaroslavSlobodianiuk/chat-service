package com.playtika.repository;

import com.playtika.model.ChatMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;


public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {

    @Query(value = "select * from user_messages where content = ?1 and time >= ?2", nativeQuery = true)
    List<ChatMessage> findMessageByData(String data, Timestamp time);

}