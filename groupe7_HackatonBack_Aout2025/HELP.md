# 🖥️ File Explorer OS - Guide de Personnalisation

## 📋 Vue d'ensemble

File Explorer OS est un système d'exploitation virtuel moderne développé avec React, TypeScript et Spring Boot. Ce guide vous accompagne dans la personnalisation de votre environnement de bureau.

## 🎨 Personnalisation de l'Apparence

### Thème et Couleurs
- **Mode d'affichage** : Choisissez entre Clair, Sombre ou Système
- **Couleur d'accent** : Personnalisez l'interface avec 6 couleurs disponibles
  - 🔵 Bleu (#3b82f6)
  - 🔴 Rouge (#ef4444)
  - 🟢 Vert (#10b981)
  - 🟠 Orange (#f59e0b)
  - 🟣 Violet (#8b5cf6)
  - 🩷 Rose (#ec4899)

### Transparence et Effets
- **Transparence des fenêtres** : Ajustez de 20% à 100%
- **Taille de police** : Personnalisez de 10px à 20px
- **Animations** : Activez/désactivez les effets visuels

## 🖼️ Fond d'écran

### Dégradés Prédéfinis
- 🌊 **Océan** : Bleu dégradé
- 🌅 **Coucher de soleil** : Orange vers rouge
- 🌲 **Forêt** : Vert dégradé
- 🟣 **Violet** : Violet dégradé
- 🩷 **Rose** : Rose dégradé
- 🌙 **Nuit** : Gris sombre

### Couleurs Unies
- 8 couleurs disponibles : Bleu, Rouge, Vert, Orange, Violet, Rose, Gris, Sombre

## ⚙️ Configuration Système

### Localisation
- **Langues supportées** : Français, English, Español, Deutsch
- **Format d'heure** : 12h (AM/PM) ou 24h
- **Format de date** : DD/MM/YYYY, MM/DD/YYYY, YYYY-MM-DD

### Fonctionnalités
- **Sauvegarde automatique** : Activez la sauvegarde en arrière-plan
- **Sons système** : Activez/désactivez les sons d'interface

## 🔔 Notifications

### Types de Notifications
- ✅ **Notifications système** : Alertes générales
- 💬 **Nouveaux messages** : Messages et communications
- 🔄 **Mises à jour système** : Informations de mise à jour
- 📅 **Rappels de calendrier** : Événements et rendez-vous
- ⏰ **Minuteur terminé** : Alertes de minuteur

### Configuration
- **Position** : Haut gauche, Haut droite, Bas gauche, Bas droite
- **Sons** : Activez/désactivez les sons de notification
- **Test** : Ajoutez une notification de test

## 🚀 Applications Disponibles

### Applications Système
- 📁 **Explorateur de fichiers** : Gestionnaire de fichiers complet
- 🧮 **Calculatrice** : Calculatrice scientifique
- 📝 **Éditeur de texte** : Éditeur avec syntax highlighting
- 🖼️ **Galerie d'images** : Visionneuse d'images
- 💻 **Terminal** : Ligne de commande virtuelle
- 📅 **Calendrier** : Gestion d'événements et rappels
- ⏰ **Horloge** : Horloge et minuteur
- 🎨 **Paint** : Éditeur graphique simple
- ⚙️ **Paramètres** : Configuration système

### Applications Multimédia
- 🎵 **Lecteur de musique** : Lecteur audio complet
- 🎵 **Mini lecteur** : Version compacte du lecteur
- 📄 **Visionneuse de fichiers** : Lecteur de documents

## 🛠️ Architecture Technique

### Frontend
- **Framework** : React 19 + TypeScript
- **Build tool** : Vite
- **Styling** : Tailwind CSS + Radix UI
- **State management** : Zustand
- **Architecture** : Composants modulaires

### Backend
- **Framework** : Spring Boot 3.5.3
- **Base de données** : MySQL
- **Architecture** : Hexagonale (Clean Architecture)
- **API** : REST avec documentation Swagger

## 📁 Système de Fichiers

### Dossiers Système
```
/ (Racine)
├── 🖥️ Bureau/     (Tous types de fichiers, drag & drop)
├── 🎵 Musique/    (Fichiers audio uniquement)
├── 🖼️ Images/     (Fichiers image uniquement)
└── 📄 Documents/  (Fichiers texte et documents)
```

### Fonctionnalités
- **Système de favoris** pour fichiers et dossiers
- **Recherche globale** dans l'arborescence
- **Upload/Download** de fichiers
- **Gestion des types MIME** avec validation
- **Navigation par breadcrumbs**

## 🔧 Développement

### Prérequis
- Node.js 18+
- Java 17+
- MySQL 8.0+

### Installation
```bash
# Frontend
cd projetFront/hackaton_aout2025
npm install
npm run dev

# Backend
cd projetBack/groupe7_HackatonBack_Aout2025
./gradlew bootRun
```

### Variables d'Environnement
```bash
# Frontend (.env)
VITE_API_URL=http://localhost:8080/api/v2
VITE_APP_NAME=File Explorer OS
VITE_APP_VERSION=1.0.0

# Backend (application.properties)
spring.datasource.url=jdbc:mysql://localhost:3306/file_explorer
spring.datasource.username=root
spring.datasource.password=yourStrong(!)Password
```

## 📞 Support

### Informations Système
- **Version** : 1.0.0
- **Développeur** : Équipe AEMT - Groupe 7
- **Technologies** : React + Vite, TypeScript, Tailwind CSS
- **Licence** : MIT License

### Actions Système
- **Vérifier les mises à jour** : Recherche de nouvelles versions
- **Rapport de bug** : Signaler un problème

---

*File Explorer OS - Un environnement de bureau moderne et personnalisable* 🎯

