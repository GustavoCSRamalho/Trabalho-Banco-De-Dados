package com.example.controller;

import com.example.model.entity.Login;
import com.example.model.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("/")
public class ChatController {

    private List<Login> contatos= new ArrayList<Login>();

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String viewApplication() {
        return "index";
    }

    @MessageMapping("/chat.{channelID}")
//    @SendTo("/topic/message.{channelID}")
    public void sendPrivateMessage(@DestinationVariable String channelId, Message message) {
//        message.setFromUserId(Long.parseLong(channelId));
        simpMessagingTemplate.convertAndSend("/user/" +channelId + "/exchange/amq.direct/chat.message", message);
    }

    @MessageMapping("/app/chat")
    @SendTo("/topic/message")
    public Message sendMessage(Message message){
        return message;
    }

    @MessageMapping("/chat/login")
    @SendTo("/topic/participantes")
    public List<Login> receiveLogin(Login login){
        contatos.add(login);
        return contatos;
    }

//    @MessageMapping("chat.{}")
}