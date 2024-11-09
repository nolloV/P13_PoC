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
  userId: number | null = null;
  role: string | null = null;
  message: string = '';
  messages: { utilisateur_id: number, prenom: string, content: string, expediteur: string }[] = [];

  constructor(private httpClient: HttpClient, private websocketService: WebsocketService) {}

  ngOnInit(): void {
    // Abonnement aux messages WebSocket dès l'initialisation du composant
    this.websocketService.getMessages().subscribe((msg: any) => {
      console.log("Message reçu :", msg);
      this.messages.push(msg);
    });
  }

  startChat() {
    if (this.role) {
      this.loadMessages();
    }
  }

  loadMessages() {
    // Charge les messages historiques en fonction du rôle
    if (this.role === 'utilisateur' && this.userId) {
      this.httpClient.get(`/api/chat/messages/${this.userId}`)
        .subscribe((messages: any) => {
          this.messages = messages;
        });
    } else if (this.role === 'service_client') {
      this.httpClient.get(`/api/chat/messages/service-client`)
        .subscribe((messages: any) => {
          this.messages = messages;
        });
    }
  }

  sendMessage() {
    if (this.message) {
      const messageData = {
        utilisateur_id: this.userId,  // Envoi de utilisateur_id directement
        content: this.message,
        expediteur: this.role === 'service_client' ? 'SERVICE_CLIENT' : 'UTILISATEUR'
      };
      this.websocketService.sendMessage(messageData);
      this.message = '';
    }
  }
}
