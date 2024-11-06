# YourCarYourWay Chat PoC

<img src="./backend\src\main\resources\static\16672296983742_P12_banner.png" alt="Your Car Your Way logo" />

## Contexte

Your Car Your Way fait des locations de voitures depuis une vingtaine d’années. Initialement en Angleterre, l’entreprise s’est étendue sur le marché européen, puis, depuis l’an dernier, sur le marché américain.

Cette croissance n’avait pas été anticipée du point de vue du système d’information. Selon les pays, les clients utilisent des applications web distinctes. Ce repository contient une preuve de concept (PoC) pour la fonctionnalité de chat en ligne, permettant aux clients de communiquer avec le service client via un chat en ligne.

## Technologies utilisées

- **Frontend** : Angular 18 ([Télécharger Angular](https://angular.io/guide/setup-local))
- **Backend** : Spring Boot 2.5.4 ([Télécharger Spring Boot](https://spring.io/projects/spring-boot))
- **Java** : Java 17 ([Télécharger Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html))
- **WebSocket** : STOMP over SockJS

## Installation

### Cloner le repository

```bash
git clone https://github.com/YourUsername/YourCarYourWay-ChatPoC.git
cd YourCarYourWay-ChatPoC
```

### Backend (Spring Boot)

1. **Télécharger et installer les dépendances**

   Assurez-vous d'avoir [Maven](https://maven.apache.org/) installé.

   ```bash
   cd chat-backend
   ./mvnw clean install
   ```
2. **Lancer le serveur backend**
```bash
./mvnw spring-boot:run
```

### Frontend (Angular)

1. **Télécharger et installer les dépendances**

   Assurez-vous d'avoir [Node.js](https://nodejs.org/) et [npm](https://www.npmjs.com/) installés.

   ```bash
   cd chat-frontend
   npm install
   ```
2. **Lancer le serveur frontend**
   ```bash
    npm start
   ```
   ## Utilisation

1. Ouvrez votre navigateur et accédez à `http://localhost:4200`.
2. Entrez un nom d'utilisateur et un message, puis cliquez sur "Envoyer".
3. Ouvrez une autre fenêtre ou un autre navigateur pour simuler un autre utilisateur et répétez les étapes.

## Résultat attendu

Un repository avec le code de la preuve de concept (PoC) pour la fonctionnalité de chat en ligne.