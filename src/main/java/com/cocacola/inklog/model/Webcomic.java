/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocacola.inklog.model;

import java.time.LocalDate;
import java.util.List;

import com.cocacola.inklog.model.enums.StatutLecture;
import com.cocacola.inklog.model.enums.TypeOuvrage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author tetra
 */

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Webcomic extends Oeuvre {

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeOuvrage type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatutLecture statut;

    @Min(0)
    private Integer chaptotal;

    @OneToOne(mappedBy = "webcomic")
    @JsonIgnoreProperties("webcomic")
    private LectureSuivi lectureSuivi;

    public Webcomic() {
        
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
    public Integer getChaptotal() {
        return chaptotal;
    }
    public void setChaptotal(Integer chaptotal) {
        this.chaptotal = chaptotal;
    }

    public LectureSuivi getLectureSuivi() {
        return lectureSuivi;
    }

    public void setLectureSuivi(LectureSuivi lectureSuivi) {
        this.lectureSuivi = lectureSuivi;
    }

    public Webcomic(Long id, String titre, TypeOuvrage type, StatutLecture statut, String synopsis, Integer chaptotal, LocalDate dateAjout, Auteur auteur, List<Genre> genres) {
        setId(id);
        setTitre(titre);
        setType(type);
        this.statut = statut;
        setSynopsis(synopsis);
        this.chaptotal = chaptotal;
        setDateAjout(dateAjout);
        setAuteur(auteur);
        setGenres(genres);
    }
}
