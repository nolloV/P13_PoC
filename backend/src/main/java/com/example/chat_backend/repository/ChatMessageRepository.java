package com.example.chat_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chat_backend.model.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {


    List<ChatMessage> findByUtilisateurIdAndExpediteur(Long utilisateurId, ChatMessage.Expéditeur expediteur);

    List<ChatMessage> findByExpediteur(ChatMessage.Expéditeur expediteur);
}
