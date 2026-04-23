package com.cocacola.inklog.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class LectureSuivi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(0)
    private int chapitreActuel;

    @Min(1)
    @Max(10)
    @Column(nullable = true)
    private Integer note;

    private String avis;
    private LocalDate dateDebutLecture;
    private LocalDate dateDerniereLecture;

    @NotNull
    @OneToOne
    @JoinColumn(name = "webcomic_id", nullable = false, unique = true)
    @JsonIgnoreProperties("lectureSuivi")
    private Webcomic webcomic;

    public LectureSuivi() {
    }

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

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public LocalDate getDateDebutLecture() {
        return dateDebutLecture;
    }

    public void setDateDebutLecture(LocalDate dateDebutLecture) {
        this.dateDebutLecture = dateDebutLecture;
    }

    public LocalDate getDateDerniereLecture() {
        return dateDerniereLecture;
    }

    public void setDateDerniereLecture(LocalDate dateDerniereLecture) {
        this.dateDerniereLecture = dateDerniereLecture;
    }

    public Webcomic getWebcomic() {
        return webcomic;
    }

    public void setWebcomic(Webcomic webcomic) {
        this.webcomic = webcomic;
    }

    @PrePersist
    public void onCreate() {
        if (dateDebutLecture == null) {
            dateDebutLecture = LocalDate.now();
        }
        dateDerniereLecture = LocalDate.now();
    }

    @PreUpdate
    public void onUpdate() {
        dateDerniereLecture = LocalDate.now();
    }

}
