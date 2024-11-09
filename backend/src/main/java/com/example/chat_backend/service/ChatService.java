package com.example.chat_backend.service;

import com.example.chat_backend.model.ChatMessage;
import com.example.chat_backend.model.Utilisateur;
import com.example.chat_backend.repository.ChatMessageRepository;
import com.example.chat_backend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public void addMessage(ChatMessage message) {
        chatMessageRepository.save(message);
        System.out.println("Message enregistré : " + message.getContent());
    }
    

    public List<ChatMessage> getMessagesByUser(Long utilisateurId) {
        return chatMessageRepository.findByUtilisateurIdAndExpediteur(utilisateurId, ChatMessage.Expéditeur.UTILISATEUR);
    }

    public List<ChatMessage> getMessagesByRole(ChatMessage.Expéditeur expediteur) {
        return chatMessageRepository.findByExpediteur(expediteur);
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }
}
