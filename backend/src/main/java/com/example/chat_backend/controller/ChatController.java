package com.example.chat_backend.controller;

import com.example.chat_backend.DTO.ChatMessageDTO;
import com.example.chat_backend.model.ChatMessage;
import com.example.chat_backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessageDTO sendMessage(ChatMessageDTO message) {
        chatService.addMessage(message);
        return message;
    }

    @GetMapping("/messages/{userId}")
    public List<ChatMessageDTO> getUserMessages(@PathVariable Long userId) {
        return chatService.getMessagesByUser(userId);
    }

    @GetMapping("/messages/service-client")
    public List<ChatMessageDTO> getServiceClientMessages() {
        return chatService.getMessagesByRole(ChatMessage.Expéditeur.SERVICE_CLIENT);
    }
}