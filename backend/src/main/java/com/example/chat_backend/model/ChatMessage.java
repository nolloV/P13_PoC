package com.example.chat_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private Expéditeur expediteur;

    @Column(name = "utilisateur_id")
    private Long utilisateurId; // Utilisation de utilisateurId pour faire référence à l'utilisateur

    private LocalDateTime timestamp;

    public enum Expéditeur {
        UTILISATEUR,
        SERVICE_CLIENT
    }

    // Constructeur par défaut
    public ChatMessage() {}

    // Constructeur avec paramètres
    public ChatMessage(String content, Expéditeur expediteur, Long utilisateurId) {
        this.content = content;
        this.expediteur = expediteur;
        this.utilisateurId = utilisateurId;
        this.timestamp = LocalDateTime.now();
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Expéditeur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Expéditeur expediteur) {
        this.expediteur = expediteur;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
