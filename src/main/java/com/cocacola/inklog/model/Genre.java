package com.cocacola.inklog.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

//POJO:
//Encapsulé
//Constructeur vide
//Serializable
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nom;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private List<Webcomic> webcomics = new ArrayList<>();

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

    public List<Webcomic> getWebcomics() {
        return webcomics;
    }

    public void setWebcomics(List<Webcomic> webcomics) {
        this.webcomics = webcomics;
    }

}
