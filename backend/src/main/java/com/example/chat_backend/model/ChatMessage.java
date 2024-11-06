package com.example.chat_backend.model;

public class ChatMessage {

    // Contenu du message
    private String content;
    
    // Utilisateur qui a envoyé le message
    private String user;

    // Constructeur par défaut
    public ChatMessage() {
    }

    // Constructeur avec paramètres pour initialiser le contenu et l'utilisateur
    public ChatMessage(String content, String user) {
        this.content = content;
        this.user = user;
    }

    // Getter pour le contenu du message
    public String getContent() {
        return content;
    }

    // Setter pour le contenu du message
    public void setContent(String content) {
        this.content = content;
    }

    // Getter pour l'utilisateur
    public String getUser() {
        return user;
    }

    // Setter pour l'utilisateur
    public void setUser(String user) {
        this.user = user;
    }
}