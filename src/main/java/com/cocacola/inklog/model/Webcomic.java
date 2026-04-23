/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocacola.inklog.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.cocacola.inklog.model.enums.StatutLecture;
import com.cocacola.inklog.model.enums.TypeOuvrage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author tetra
 */

@Entity
public class Webcomic implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titre;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeOuvrage type;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatutLecture statut;
    private String synopsis;
    @Min(0)
    private Integer chaptotal;
    private LocalDate dateAjout;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "auteur_id", nullable = false)
    @JsonIgnoreProperties("webcomics")
    private Auteur auteur;

    @ManyToMany
    @JoinTable(
        name = "webcomic_genre",
        joinColumns = @JoinColumn(name = "webcomic_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonIgnoreProperties("webcomics")
    private List<Genre> genres;

    @OneToOne(mappedBy = "webcomic")
    @JsonIgnoreProperties("webcomic")
    private LectureSuivi lectureSuivi;

    public Webcomic() {
        
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public TypeOuvrage getType() {
        return type;
    }
    public void setType(TypeOuvrage type) {
        this.type = type;
    }
    public StatutLecture getStatut() {
        return statut;
    }
    public void setStatut(StatutLecture statut) {
        this.statut = statut;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public Integer getChaptotal() {
        return chaptotal;
    }
    public void setChaptotal(Integer chaptotal) {
        this.chaptotal = chaptotal;
    }
    public LocalDate getDateAjout() {
        return dateAjout;
    }
    public void setDateAjout(LocalDate dateAjout) {
        this.dateAjout = dateAjout;
    }
    public Auteur getAuteur() {
        return auteur;
    }
    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
    public List<Genre> getGenres() {
        return genres;
    }
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public LectureSuivi getLectureSuivi() {
        return lectureSuivi;
    }

    public void setLectureSuivi(LectureSuivi lectureSuivi) {
        this.lectureSuivi = lectureSuivi;
    }

    public Webcomic(Long id, String titre, TypeOuvrage type, StatutLecture statut, String synopsis, Integer chaptotal, LocalDate dateAjout, Auteur auteur, List<Genre> genres) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.statut = statut;
        this.synopsis = synopsis;
        this.chaptotal = chaptotal;
        this.dateAjout = dateAjout;
        this.auteur = auteur;
        this.genres = genres;
    }

    @PrePersist
    public void onCreate() {
        if (dateAjout == null) {
            dateAjout = LocalDate.now();
        }
    }
}
