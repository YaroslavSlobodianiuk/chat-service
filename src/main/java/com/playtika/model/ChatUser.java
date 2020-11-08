package com.playtika.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class ChatUser {

    @Id
    private String userName;
    private Timestamp joinTime;

}
