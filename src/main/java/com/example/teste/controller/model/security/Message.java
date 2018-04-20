package com.example.teste.controller.model.security;

import lombok.Data;

@Data
public class Message {

    private String contents;

    private long fromUserId;

    private long toUserId;


    public Message() {

    }

    public Message(String contents,long fromUserId,long toUserId) {
        this.contents = contents;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }
}