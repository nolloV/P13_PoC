package com.example.chat_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Indique que cette classe est une entité JPA, mappée sur une table dans la base de données
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id; // Identifiant unique du message

    private String content; // Contenu du message

    @Enumerated(EnumType.STRING) // Stockage de l'enum sous forme de chaîne de caractères dans la base de données
    private Expéditeur expediteur; // Rôle de l'expéditeur (utilisateur ou service client)

    @Column(name = "utilisateur_id") // Nom de la colonne correspondant dans la base de données
    private Long utilisateurId; // ID de l'utilisateur qui a envoyé le message

    @Column(nullable = false) // Assure que le champ timestamp ne soit jamais null
    private LocalDateTime timestamp; // Date et heure de l'envoi du message

    // Enumération pour définir les types d'expéditeurs
    public enum Expéditeur {
        UTILISATEUR, // Expéditeur est un utilisateur
        SERVICE_CLIENT // Expéditeur est le service client
    }

    // Constructeur par défaut
    public ChatMessage() {
        this.timestamp = LocalDateTime.now(); // Initialise le timestamp avec la date et l'heure actuelles
    }

    // Constructeur avec paramètres
    public ChatMessage(String content, Expéditeur expediteur, Long utilisateurId) {
        this.content = content; // Initialise le contenu du message
        this.expediteur = expediteur; // Initialise l'expéditeur
        this.utilisateurId = utilisateurId; // Associe l'ID de l'utilisateur
        this.timestamp = LocalDateTime.now(); // Initialise le timestamp avec la date et l'heure actuelles
    }

    // Getters et setters pour chaque champ

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
