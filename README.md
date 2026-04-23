# 📖 Projet 1 — Bibliothèque Personnelle de Webcomics
> Niveau : **Débutant** | Stack : Spring Boot 3.x · JPA · PostgreSQL

---

## 🗂️ Entités & Champs complets

### `Auteur`
| Champ | Type | Contraintes |
|---|---|---|
| id | Long | PK, auto-généré |
| nom | String | NotBlank |
| pseudonyme | String | — |
| nationalite | String | NotBlank |

---

### `Genre`
| Champ | Type | Contraintes |
|---|---|---|
| id | Long | PK, auto-généré |
| nom | String | NotBlank, Unique |

Exemples de valeurs : `Isekai`, `Action`, `Romance`, `Cultivation`, `Slice of Life`, `Horreur`, `Fantaisie`

---

### `Webcomic`
| Champ | Type | Contraintes |
|---|---|---|
| id | Long | PK, auto-généré |
| titre | String | NotBlank |
| type | Enum | MANGA / MANHWA / MANHUA |
| statut | Enum | EN_COURS / TERMINE / EN_PAUSE / ABANDONNE |
| synopsis | String | — |
| chapitreTotal | Integer | Min(0), null si en cours |
| dateAjout | LocalDate | auto à la création |
| auteur | Auteur | ManyToOne, NotNull |
| genres | List\<Genre\> | ManyToMany |

---

### `LectureSuivi`
| Champ | Type | Contraintes |
|---|---|---|
| id | Long | PK, auto-généré |
| chapitreActuel | Integer | Min(0), NotNull |
| note | Integer | Min(1), Max(10), nullable |
| avis | String | nullable |
| dateDebutLecture | LocalDate | — |
| dateDerniereLecture | LocalDate | auto à chaque update |
| webcomic | Webcomic | OneToOne, NotNull |

---

## 🔗 Relations JPA

```
Auteur      (1) ──────── (N) Webcomic
Genre       (N) ──────── (N) Webcomic     [table : webcomic_genre]
Webcomic    (1) ──────── (1) LectureSuivi
```

---

## 📋 CRUD Complet par Entité

---

### 👤 Auteur — `/api/auteurs`

| Méthode | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auteurs` | Créer un auteur |
| `GET` | `/api/auteurs` | Lister tous les auteurs |
| `GET` | `/api/auteurs/{id}` | Détail d'un auteur |
| `PUT` | `/api/auteurs/{id}` | Modifier un auteur |
| `DELETE` | `/api/auteurs/{id}` | Supprimer un auteur |

> ⚠️ **Règle** : on ne peut pas supprimer un auteur s'il a des webcomics associés → réponse `409 Conflict`

---

### 🏷️ Genre — `/api/genres`

| Méthode | Endpoint | Description |
|---|---|---|
| `POST` | `/api/genres` | Créer un genre |
| `GET` | `/api/genres` | Lister tous les genres |
| `GET` | `/api/genres/{id}` | Détail d'un genre |
| `PUT` | `/api/genres/{id}` | Modifier un genre |
| `DELETE` | `/api/genres/{id}` | Supprimer un genre |

> ⚠️ **Règle** : le nom du genre doit être unique → `400 Bad Request` si doublon

---

### 📚 Webcomic — `/api/webcomics`

| Méthode | Endpoint | Description |
... (145lignes restantes)

projet1-webcomics-detail.md
8 Ko
﻿
# 📖 Projet 1 — Bibliothèque Personnelle de Webcomics
> Niveau : **Débutant** | Stack : Spring Boot 3.x · JPA · PostgreSQL

---

## 🗂️ Entités & Champs complets

### `Auteur`
| Champ | Type | Contraintes |
|---|---|---|
| id | Long | PK, auto-généré |
| nom | String | NotBlank |
| pseudonyme | String | — |
| nationalite | String | NotBlank |

---

### `Genre`
| Champ | Type | Contraintes |
|---|---|---|
| id | Long | PK, auto-généré |
| nom | String | NotBlank, Unique |

Exemples de valeurs : `Isekai`, `Action`, `Romance`, `Cultivation`, `Slice of Life`, `Horreur`, `Fantaisie`

---

### `Webcomic`
| Champ | Type | Contraintes |
|---|---|---|
| id | Long | PK, auto-généré |
| titre | String | NotBlank |
| type | Enum | MANGA / MANHWA / MANHUA |
| statut | Enum | EN_COURS / TERMINE / EN_PAUSE / ABANDONNE |
| synopsis | String | — |
| chapitreTotal | Integer | Min(0), null si en cours |
| dateAjout | LocalDate | auto à la création |
| auteur | Auteur | ManyToOne, NotNull |
| genres | List\<Genre\> | ManyToMany |

---

### `LectureSuivi`
| Champ | Type | Contraintes |
|---|---|---|
| id | Long | PK, auto-généré |
| chapitreActuel | Integer | Min(0), NotNull |
| note | Integer | Min(1), Max(10), nullable |
| avis | String | nullable |
| dateDebutLecture | LocalDate | — |
| dateDerniereLecture | LocalDate | auto à chaque update |
| webcomic | Webcomic | OneToOne, NotNull |

---

## 🔗 Relations JPA

```
Auteur      (1) ──────── (N) Webcomic
Genre       (N) ──────── (N) Webcomic     [table : webcomic_genre]
Webcomic    (1) ──────── (1) LectureSuivi
```

---

## 📋 CRUD Complet par Entité

---

### 👤 Auteur — `/api/auteurs`

| Méthode | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auteurs` | Créer un auteur |
| `GET` | `/api/auteurs` | Lister tous les auteurs |
| `GET` | `/api/auteurs/{id}` | Détail d'un auteur |
| `PUT` | `/api/auteurs/{id}` | Modifier un auteur |
| `DELETE` | `/api/auteurs/{id}` | Supprimer un auteur |

> ⚠️ **Règle** : on ne peut pas supprimer un auteur s'il a des webcomics associés → réponse `409 Conflict`

---

### 🏷️ Genre — `/api/genres`

| Méthode | Endpoint | Description |
|---|---|---|
| `POST` | `/api/genres` | Créer un genre |
| `GET` | `/api/genres` | Lister tous les genres |
| `GET` | `/api/genres/{id}` | Détail d'un genre |
| `PUT` | `/api/genres/{id}` | Modifier un genre |
| `DELETE` | `/api/genres/{id}` | Supprimer un genre |

> ⚠️ **Règle** : le nom du genre doit être unique → `400 Bad Request` si doublon

---

### 📚 Webcomic — `/api/webcomics`

| Méthode | Endpoint | Description |
|---|---|---|
| `POST` | `/api/webcomics` | Créer un webcomic |
| `GET` | `/api/webcomics` | Lister tous les webcomics |
| `GET` | `/api/webcomics/{id}` | Détail d'un webcomic |
| `PUT` | `/api/webcomics/{id}` | Modifier un webcomic |
| `DELETE` | `/api/webcomics/{id}` | Supprimer un webcomic |

> ⚠️ **Règles** :
> - `chapitreTotal` ne peut pas être renseigné si `statut = EN_COURS`
> - On ne peut pas supprimer un webcomic s'il a un suivi de lecture actif → `409 Conflict`
> - `dateAjout` est automatiquement définie à la création, non modifiable

---

### 📊 Lecture Suivi — `/api/suivis`

| Méthode | Endpoint | Description |
|---|---|---|
| `POST` | `/api/suivis` | Créer un suivi pour un webcomic |
| `GET` | `/api/suivis` | Lister tous les suivis |
| `GET` | `/api/suivis/{id}` | Détail d'un suivi |
| `PUT` | `/api/suivis/{id}` | Modifier un suivi (note, avis…) |
| `DELETE` | `/api/suivis/{id}` | Supprimer un suivi |

> ⚠️ **Règles** :
> - Un webcomic ne peut avoir **qu'un seul suivi** (relation OneToOne) → `409 Conflict` si déjà existant
> - `chapitreActuel` ne peut pas dépasser `chapitreTotal` du webcomic (si renseigné)
> - `dateDerniereLecture` est mise à jour automatiquement à chaque modification

---

## ⚙️ Endpoints Métier

### Filtres & Recherche
| Méthode | Endpoint | Description |
|---|---|---|
| `GET` | `/api/webcomics/en-cours` | Webcomics avec statut `EN_COURS` |
| `GET` | `/api/webcomics/termines` | Webcomics avec statut `TERMINE` |
| `GET` | `/api/webcomics/abandonnes` | Webcomics avec statut `ABANDONNE` |
| `GET` | `/api/webcomics/type/{type}` | Filtrer par MANGA / MANHWA / MANHUA |
| `GET` | `/api/webcomics/genre/{genreId}` | Tous les webcomics d'un genre |
| `GET` | `/api/webcomics/auteur/{auteurId}` | Tous les webcomics d'un auteur |
| `GET` | `/api/webcomics/recherche?titre=` | Recherche par mot-clé dans le titre |

### Progression
| Méthode | Endpoint | Description |
|---|---|---|
| `PUT` | `/api/suivis/{id}/avancer?chapitres=` | Incrémenter la progression |
| `PUT` | `/api/suivis/{id}/reculer?chapitres=` | Corriger une erreur de progression |
| `PUT` | `/api/webcomics/{id}/statut?valeur=` | Changer le statut d'un webcomic |

### Statistiques & Classements
| Méthode | Endpoint | Description |
|---|---|---|
| `GET` | `/api/webcomics/top` | Classement par note décroissante |
| `GET` | `/api/webcomics/recents` | Les 5 derniers webcomics consultés |
| `GET` | `/api/stats/resume` | Résumé global de la bibliothèque |

---

## 📊 Détail : `GET /api/stats/resume`

Retourne un objet JSON de synthèse :

```json
{
  "totalWebcomics": 42,
  "enCours": 12,
  "termines": 25,
  "enPause": 3,
  "abandonnes": 2,
  "noteMoyenne": 7.4,
  "chapitresLusTotaux": 3871,
  "genrePreferr": "Isekai",
  "typePreferr": "MANHWA"
}
```

---

## 🚦 Codes HTTP utilisés

| Code | Cas d'usage |
|---|---|
| `200 OK` | Lecture / modification réussie |
| `201 Created` | Création réussie |
| `204 No Content` | Suppression réussie |
| `400 Bad Request` | Données invalides (validation échouée) |
| `404 Not Found` | Ressource introuvable |
| `409 Conflict` | Règle métier violée (doublon, contrainte…) |

---

## 📁 Structure du projet suggérée

```
src/main/java/com/toi/webcomics/
├── controller/
│   ├── AuteurController.java
│   ├── GenreController.java
│   ├── WebcomicController.java
│   ├── LectureSuiviController.java
│   └── StatsController.java
├── service/
│   ├── AuteurService.java
│   ├── GenreService.java
│   ├── WebcomicService.java
│   └── LectureSuiviService.java
├── repository/
│   ├── AuteurRepository.java
│   ├── GenreRepository.java
│   ├── WebcomicRepository.java
│   └── LectureSuiviRepository.java
├── model/
│   ├── Auteur.java
│   ├── Genre.java
│   ├── Webcomic.java
│   ├── LectureSuivi.java
│   └── enums/
│       ├── TypeWebcomic.java    (MANGA, MANHWA, MANHUA)
│       └── StatutWebcomic.java  (EN_COURS, TERMINE, EN_PAUSE, ABANDONNE)
├── dto/
│   ├── WebcomicDTO.java
│   ├── LectureSuiviDTO.java
│   └── StatsResumeDTO.java
└── exception/
    ├── ResourceNotFoundException.java
    └── BusinessRuleException.java
```

---

## 🧩 Ordre de développement conseillé

1. ✅ Créer les entités + enums + relations JPA
2. ✅ Configurer PostgreSQL dans `application.properties`
3. ✅ Implémenter les Repository (Spring Data JPA — méthodes auto)
4. ✅ CRUD Auteur (le plus simple, pas de dépendances)
5. ✅ CRUD Genre
6. ✅ CRUD Webcomic (avec relation vers Auteur et Genre)
7. ✅ CRUD LectureSuivi (avec règles OneToOne et validation)
8. ✅ Endpoints métier (filtres, progression, stats)
9. ✅ Gestion des exceptions globale (`@ControllerAdvice`)
10. ✅ Tester via Swagger UI
