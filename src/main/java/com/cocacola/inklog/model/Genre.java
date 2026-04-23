package com.cocacola.inklog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//POJO:
//Encapsulé
//Constructeur vide
//Serializable
@Entity
public class Genre {
    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    public Genre() {

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

    public Genre(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

}
