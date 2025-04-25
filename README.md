# API Plants vs. Zombies 🌱🧟‍♂️

API REST pour la gestion des ressources du jeu Plants vs. Zombies, développée avec Spring MVC et sécurisée avec Spring Security.

## Fonctionnalités

- Gestion des plantes (ajout, modification, suppression, consultation)
- Gestion des zombies (ajout, modification, suppression, consultation)
- Gestion des cartes/maps (ajout, modification, suppression, consultation)
- Sécurité avec authentification HTTP Basic

## Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur
- MySQL 8.0 ou supérieur

## Démarrer le projet

1. Cloner le dépôt
2. Configurer la base de données dans `AppConfig.java`
3. Exécuter la commande : `mvn clean package`
4. Déployer le fichier WAR sur un serveur Tomcat

## Authentification

L'API est sécurisée avec une authentification HTTP Basic. Deux comptes utilisateurs sont préconfigurés :

| Utilisateur | Mot de passe | Rôle        | Usage                             |
|-------------|--------------|-------------|-----------------------------------|
| admin       | pvz2025      | ROLE_ADMIN  | Accès complet à toutes les API    |
| api         | api2025      | ROLE_USER   | Accès en lecture aux ressources   |

### Utilisation de l'authentification

#### 1. Avec Postman

- Dans l'onglet "Authorization", sélectionnez "Basic Auth"
- Entrez le nom d'utilisateur et le mot de passe
- Ou ajoutez manuellement l'en-tête : `Authorization: Basic YWRtaW46cHZ6MjAyNQ==` (pour admin/pvz2025)

#### 2. Avec un client JavaScript (React, Vue, etc.)

```javascript
// Exemple avec axios
import axios from 'axios';

// Fonction pour encoder en Base64
const encodeCredentials = (username, password) => {
  return btoa(`${username}:${password}`);
};

// Configuration pour les requêtes authentifiées
const apiClient = axios.create({
  baseURL: 'http://localhost:8080/CoursEpfBack',
  headers: {
    'Authorization': `Basic ${encodeCredentials('admin', 'pvz2025')}`,
    'Content-Type': 'application/json'
  }
});

// Exemple d'utilisation
apiClient.get('/plantes')
  .then(response => console.log(response.data))
  .catch(error => console.error('Erreur d\'authentification', error));
```

#### 3. Avec curl

```bash
curl -X GET "http://localhost:8080/CoursEpfBack/plantes" -H "accept: application/json" -H "Authorization: Basic YWRtaW46cHZ6MjAyNQ=="
```


## Points d'accès (endpoints) sécurisés

Les endpoints suivants nécessitent une authentification :

- `/plantes/**` - Gestion des plantes
- `/zombies/**` - Gestion des zombies
- `/maps/**` - Gestion des cartes



