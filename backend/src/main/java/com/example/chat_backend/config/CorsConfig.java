package com.example.chat_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // Déclare un bean WebMvcConfigurer pour configurer les mappings CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            // Surcharge de la méthode addCorsMappings pour ajouter des configurations CORS
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                // Ajoute un mapping CORS pour toutes les routes (/**)
                registry.addMapping("/**")
                        // Autorise les requêtes provenant de http://localhost:4200
                        .allowedOrigins("http://localhost:4200")
                        // Autorise les méthodes HTTP spécifiées
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Autorise tous les en-têtes
                        .allowedHeaders("*")
                        // Autorise l'envoi des informations d'authentification (cookies, headers d'autorisation)
                        .allowCredentials(true);
            }
        };
    }
}