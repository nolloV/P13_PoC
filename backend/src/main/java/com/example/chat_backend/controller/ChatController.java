package com.example.chat_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat_backend.model.ChatMessage;
import com.example.chat_backend.service.ChatService;

@Controller
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        // Sauvegarde du message sans chercher l'objet Utilisateur
        chatService.addMessage(message);
        return message;
    }

    @GetMapping("/messages/{userId}")
    public List<ChatMessage> getUserMessages(@PathVariable Long userId) {
        return chatService.getMessagesByUser(userId);
    }

    @GetMapping("/messages/service-client")
    public List<ChatMessage> getServiceClientMessages() {
        return chatService.getMessagesByRole(ChatMessage.Expéditeur.SERVICE_CLIENT);
    }
}
