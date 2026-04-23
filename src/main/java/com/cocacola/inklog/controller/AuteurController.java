package com.cocacola.inklog.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cocacola.inklog.model.Auteur;
import com.cocacola.inklog.repository.AuteurRepository;

@RestController
public class AuteurController {

    @Autowired
    private AuteurRepository auteurRepository;

    @PostMapping("/auteurs/")
    public ResponseEntity<Auteur> create(@RequestBody Auteur auteur) {

        auteur = auteurRepository.save(auteur);

        return ResponseEntity.ok(auteur);
    }

    @GetMapping("/auteurs/")
    public List<Auteur> getAll() {
        return (List<Auteur>) auteurRepository.findAll();
    }

    @GetMapping("/auteurs/{id}")
    public ResponseEntity<Auteur> get(@PathVariable Long id) {
        Auteur a;

        try {
            a = auteurRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(a);
    }

    @PutMapping("/auteurs/{id}")
    public ResponseEntity<Auteur> update(@PathVariable Long id, @RequestBody Auteur auteur) {
        Auteur a;

        try {
            a = auteurRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
        a.setNom(auteur.getNom());
        a.setPseudonyme(auteur.getPseudonyme());
        a.setNationalite(auteur.getNationalite());
        a = auteurRepository.save(a);
        return ResponseEntity.ok(a);
    }

    @DeleteMapping("/auteurs/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            auteurRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok().build();
    }
}
