package com.playtika.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity(name = "user_messages")
public class ChatMessage {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 32, columnDefinition = "varchar(32) default 'CHAT'")
   @Enumerated(value = EnumType.STRING)
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
