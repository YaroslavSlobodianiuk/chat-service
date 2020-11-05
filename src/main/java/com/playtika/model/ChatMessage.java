package com.playtika.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChatMessage {

   private MessageType type;
   private String content;
   private String sender;
   private Timestamp time;

   public enum MessageType {
      CHAT,
      JOIN,
      LEAVE
   }


}
