import { Injectable } from '@angular/core';
import { Client, Message, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private client: Client;
  private messages: Subject<{ utilisateur_id: number, content: string }>;

  constructor() {
    this.client = new Client({
      brokerURL: 'ws://localhost:8080/chat',
      connectHeaders: {
        login: 'guest',
        passcode: 'guest'
      },
      debug: (str) => {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      webSocketFactory: () => {
        return new SockJS('http://localhost:8080/chat');
      }
    });

    this.client.onConnect = (frame) => {
      console.log('Connected: ' + frame);
      this.client.subscribe('/topic/messages', (message: Message) => {
        this.messages.next(JSON.parse(message.body));
      });
    };

    this.client.onStompError = (frame) => {
      console.error('Broker reported error: ' + frame.headers['message']);
      console.error('Additional details: ' + frame.body);
    };

    this.client.activate();
    this.messages = new Subject<{ utilisateur_id: number, content: string }>();
  }

  // Mise à jour pour accepter utilisateur_id
  sendMessage(message: { utilisateur_id: number | null, content: string }) {
    this.client.publish({
      destination: '/app/message',
      body: JSON.stringify(message)
    });
  }

  getMessages(): Observable<{ utilisateur_id: number, content: string }> {
    return this.messages.asObservable();
  }
}
