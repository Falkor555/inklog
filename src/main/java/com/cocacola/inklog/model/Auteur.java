package com.cocacola.inklog.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

//POJO:
//Encapsulé
//Constructeur vide
//Serializable
@Entity
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String nom;
    private String pseudonyme;
    @NotBlank
    private String nationalite;

    @JsonIgnore
    @OneToMany(mappedBy = "auteur")
    private List<Webcomic> webcomics = new ArrayList<>();

    public Auteur() {
    }

    public Auteur(Long id, String nom, String pseudonyme, String nationalite) {
        this.id = id;
        this.nom = nom;
        this.pseudonyme = pseudonyme;
        this.nationalite = nationalite;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPseudonyme() {
        return pseudonyme;
    }
    public void setPseudonyme(String pseudonyme) {
        this.pseudonyme = pseudonyme;
    }
    public String getNationalite() {
        return nationalite;
    }
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public List<Webcomic> getWebcomics() {
        return webcomics;
    }

    public void setWebcomics(List<Webcomic> webcomics) {
        this.webcomics = webcomics;
    }

}
