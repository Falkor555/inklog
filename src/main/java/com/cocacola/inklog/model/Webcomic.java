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

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

/**
 *
 * @author tetra
 */

@Entity
public class Webcomic implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String titre;
    @Enumerated(EnumType.STRING)
    private TypeOuvrage type;
    @Enumerated(EnumType.STRING)
    private StatutLecture statut;
    private String synopsis;
    private Integer chaptotal;
    private LocalDate dateAjout;
    private String auteur;
    @ElementCollection
    private List<String> genres;

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
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Webcomic(Long id, String titre, TypeOuvrage type, StatutLecture statut, String synopsis, Integer chaptotal, LocalDate dateAjout, String auteur, List<String> genres) {
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
