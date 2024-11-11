package com.example.chat_backend.service;

import com.example.chat_backend.DTO.ChatMessageDTO;
import com.example.chat_backend.model.ChatMessage;
import com.example.chat_backend.model.Utilisateur;
import com.example.chat_backend.repository.ChatMessageRepository;
import com.example.chat_backend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public void addMessage(ChatMessageDTO messageDTO) {
        ChatMessage message = new ChatMessage();
        message.setContent(messageDTO.getContent());
        message.setExpediteur(ChatMessage.Expéditeur.valueOf(messageDTO.getExpediteur()));
        message.setUtilisateurId(messageDTO.getUtilisateurId());
        chatMessageRepository.save(message);
        System.out.println("Message enregistré : " + message.getContent());
    }

    public List<ChatMessageDTO> getMessagesByUser(Long utilisateurId) {
        return chatMessageRepository.findByUtilisateurIdAndExpediteur(utilisateurId, ChatMessage.Expéditeur.UTILISATEUR)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ChatMessageDTO> getMessagesByRole(ChatMessage.Expéditeur expediteur) {
        return chatMessageRepository.findByExpediteur(expediteur)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    private ChatMessageDTO convertToDTO(ChatMessage message) {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setId(message.getId());
        dto.setContent(message.getContent());
        dto.setExpediteur(message.getExpediteur().name());
        dto.setUtilisateurId(message.getUtilisateurId());
        dto.setTimestamp(message.getTimestamp());
        return dto;
    }
}