package com.example.chat_backend.controller;

import com.example.chat_backend.model.ChatMessage;
import com.example.chat_backend.service.ChatService;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Méthode appelée après la construction du bean pour envoyer les anciens messages aux nouveaux clients
    @PostConstruct
    public void init() {
        // Récupère les anciens messages du service de chat
        List<ChatMessage> messages = chatService.getMessages();
        // Envoie chaque message à la destination "/topic/messages"
        for (ChatMessage message : messages) {
            messagingTemplate.convertAndSend("/topic/messages", message);
        }
    }

    // Méthode pour gérer les messages envoyés via WebSocket
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        // Échappe le contenu et l'utilisateur du message pour éviter les attaques XSS
        ChatMessage sanitizedMessage = new ChatMessage(HtmlUtils.htmlEscape(message.getContent()), HtmlUtils.htmlEscape(message.getUser()));
        // Ajoute le message au service de chat
        chatService.addMessage(sanitizedMessage);
        // Retourne le message pour qu'il soit envoyé à tous les abonnés de "/topic/messages"
        return sanitizedMessage;
    }

    // Méthode pour récupérer les anciens messages via une requête HTTP GET
    @GetMapping("/messages")
    public List<ChatMessage> getMessages() {
        // Retourne la liste des messages du service de chat
        return chatService.getMessages();
    }

    // Méthode pour envoyer un message via une requête HTTP POST
    @PostMapping("/message")
    public void sendMessageViaHttp(@RequestBody ChatMessage message) {
        // Échappe le contenu et l'utilisateur du message pour éviter les attaques XSS
        ChatMessage sanitizedMessage = new ChatMessage(HtmlUtils.htmlEscape(message.getContent()), HtmlUtils.htmlEscape(message.getUser()));
        // Ajoute le message au service de chat
        chatService.addMessage(sanitizedMessage);
        // Envoie le message à la destination "/topic/messages"
        messagingTemplate.convertAndSend("/topic/messages", sanitizedMessage);
    }
}