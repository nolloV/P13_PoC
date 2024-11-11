import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { WebsocketService } from '../services/websocket.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-chat',
  standalone: true,
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
  imports: [CommonModule, FormsModule, HttpClientModule]
})
export class ChatComponent implements OnInit {
  utilisateurId: number | null = null; // ID de l'utilisateur sélectionné pour le chat
  expediteur: 'UTILISATEUR' | 'SERVICE_CLIENT' = 'UTILISATEUR'; // Rôle de l'expéditeur : utilisateur ou service client
  message: string = ''; // Contenu du message à envoyer
  messages: { utilisateur_id: number | null, prenom: string, nom: string, content: string, expediteur: string, timestamp: string }[] = []; // Liste des messages échangés
  utilisateurs: { id: number, nom: string, prenom: string }[] = []; // Liste des utilisateurs disponibles pour le chat

  constructor(private httpClient: HttpClient, private websocketService: WebsocketService) {}

  ngOnInit(): void {
    // Chargement initial des utilisateurs disponibles
    this.loadUtilisateurs();

    // Abonnement aux messages WebSocket pour recevoir les messages en temps réel
    this.websocketService.getMessages().subscribe((msg: any) => {
      // Recherche des informations de l'utilisateur qui a envoyé le message
      const user = this.utilisateurs.find(user => user.id === msg.utilisateurId);
      msg.prenom = user ? user.prenom : 'Inconnu'; // Associe le prénom de l'utilisateur, ou "Inconnu" si non trouvé
      msg.nom = user ? user.nom : 'Inconnu';       // Associe le nom de l'utilisateur, ou "Inconnu" si non trouvé
      this.messages.push(msg); // Ajoute le message reçu à la liste des messages
    });
  }

  // Fonction pour charger la liste des utilisateurs depuis le serveur
  loadUtilisateurs() {
    this.httpClient.get('http://localhost:8080/utilisateurs').subscribe((data: any) => {
      this.utilisateurs = data; // Stocke la liste des utilisateurs
    });
  }

  // Fonction pour envoyer un message
  sendMessage() {
    // Vérifie que le message n'est pas vide et qu'un utilisateur est sélectionné
    if (!this.message.trim() || !this.utilisateurId) {
      return;
    }

    // Crée un objet représentant le message à envoyer
    const messageData = {
      utilisateurId: this.utilisateurId, // ID de l'utilisateur qui envoie le message
      content: this.message.trim(),       // Contenu du message
      expediteur: this.expediteur         // Rôle de l'expéditeur (utilisateur ou service client)
    };

    // Envoie le message via le service WebSocket
    this.websocketService.sendMessage(messageData);

    // Réinitialise le champ de saisie du message
    this.message = '';
  }
}
