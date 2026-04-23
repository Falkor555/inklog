package com.cocacola.inklog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//POJO:
//Encapsulé
//Constructeur vide
//Serializable
@Entity
public class Auteur {

    @Id
    @GeneratedValue
    private Long id;
    
    private String nom;
    private String pseudonyme;
    private String nationalite;

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

}
