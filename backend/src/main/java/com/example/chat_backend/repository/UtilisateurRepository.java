package com.example.chat_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chat_backend.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
