package com.example.chat_backend.service;

import com.example.chat_backend.model.ChatMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    // Liste pour stocker les messages de chat
    private final List<ChatMessage> messages = new ArrayList<>();

    // Méthode pour ajouter un message à la liste
    public void addMessage(ChatMessage message) {
        messages.add(message);
    }

    // Méthode pour récupérer tous les messages
    public List<ChatMessage> getMessages() {
        // Retourne une nouvelle liste contenant tous les messages pour éviter les modifications directes
        return new ArrayList<>(messages);
    }
}