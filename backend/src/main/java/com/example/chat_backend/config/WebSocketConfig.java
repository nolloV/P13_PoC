package com.example.chat_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Configure le broker de messages
    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry config) {
        // Active un simple broker de messages sur le préfixe "/topic"
        config.enableSimpleBroker("/topic");
        // Définit le préfixe des destinations des messages envoyés par les clients
        config.setApplicationDestinationPrefixes("/app");
    }

    // Enregistre les points de terminaison STOMP
    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        // Enregistre le point de terminaison "/chat" et configure CORS pour autoriser les requêtes provenant de "http://localhost:4200"
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:4200")
                // Utilise SockJS pour les clients qui ne supportent pas WebSocket
                .withSockJS();
    }
}