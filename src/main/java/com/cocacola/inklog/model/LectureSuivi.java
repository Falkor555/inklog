package com.cocacola.inklog.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LectureSuivi {
    private Long id;
    private int chapitreActuel;
    private int note;
    private String avis;
    private Date dateDebutLecture;
    private Date dateDerniereLecture;
    // private Webcomic webcomic;
    public LectureSuivi() {
    }
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getChapitreActuel() {
        return chapitreActuel;
    }
    public void setChapitreActuel(int chapitreActuel) {
        this.chapitreActuel = chapitreActuel;
    }
    public int getNote() {
        return note;
    }
    public void setNote(int note) {
        this.note = note;
    }
    public String getAvis() {
        return avis;
    }
    public void setAvis(String avis) {
        this.avis = avis;
    }
    public Date getDateDebutLecture() {
        return dateDebutLecture;
    }
    public void setDateDebutLecture(Date dateDebutLecture) {
        this.dateDebutLecture = dateDebutLecture;
    }
    public Date getDateDerniereLecture() {
        return dateDerniereLecture;
    }
    public void setDateDerniereLecture(Date dateDerniereLecture) {
        this.dateDerniereLecture = dateDerniereLecture;
    }
    
}
