-- Table Utilisateur
CREATE TABLE Utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    motDePasse VARCHAR(255),
    dateNaissance DATE,
    parametresSecurite JSON,  -- Paramètres de sécurité, ex: authentification à deux facteurs
    historiqueConnexion JSON  -- Historique sous forme JSON ou via une table séparée
);

-- Table HistoriqueConnexion (si l'historique de connexion est requis)
CREATE TABLE HistoriqueConnexion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilisateur_id INT,
    dateConnexion DATETIME,
    adresseIP VARCHAR(45),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id) ON DELETE CASCADE
);

-- Table Véhicule
CREATE TABLE Véhicule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marque VARCHAR(50),
    modele VARCHAR(50),
    categorie VARCHAR(50),
    disponibilite BOOLEAN,
    prixJour DECIMAL(10, 2),
    localisation VARCHAR(100) -- Emplacement actuel du véhicule
);

-- Table Réservation
CREATE TABLE Reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dateDebut DATE,
    dateFin DATE,
    statut ENUM('en_attente', 'confirmée', 'annulée'),
    prixTotal DECIMAL(10, 2),
    villeDepart VARCHAR(100),
    villeRetour VARCHAR(100),
    utilisateur_id INT,
    vehicule_id INT,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id) ON DELETE CASCADE,
    FOREIGN KEY (vehicule_id) REFERENCES Véhicule(id) ON DELETE SET NULL
);

-- Table SupportClient
CREATE TABLE SupportClient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilisateur_id INT,
    agent_id INT, -- Si un système de suivi des agents est requis, sinon NULL
    typeInteraction ENUM('chat', 'email', 'appel'),
    dateHeure DATETIME,
    message TEXT,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id) ON DELETE CASCADE
);

-- Table Paiement (avec gestion des remboursements)
CREATE TABLE Paiement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    montant DECIMAL(10, 2),
    datePaiement DATE,
    moyenPaiement VARCHAR(50),
    statut ENUM('en_attente', 'réussi', 'échoué'),
    reservation_id INT,
    statutRemboursement ENUM('en_attente', 'remboursé', 'non_remboursable'),
    FOREIGN KEY (reservation_id) REFERENCES Reservation(id) ON DELETE SET NULL
);

-- Table Message
CREATE TABLE Message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contenu TEXT,
    expediteur ENUM('UTILISATEUR', 'SERVICE_CLIENT'),
    utilisateur_id INT,
    dateEnvoi TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id) ON DELETE CASCADE
);
