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

import com.cocacola.inklog.model.Genre;
import com.cocacola.inklog.repository.GenreRepository;

@RestController
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @PostMapping("/genres/")
    public ResponseEntity<Genre> create(@RequestBody Genre genre) {
        genre = genreRepository.save(genre);
        return ResponseEntity.ok(genre);
    }

    @GetMapping("/genres/")
    public List<Genre> getAll() {
        return (List<Genre>) genreRepository.findAll();
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<Genre> get(@PathVariable Long id) {
        Genre g;

        try {
            g = genreRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(g);
    }

    @PutMapping("/genres/{id}")
    public ResponseEntity<Genre> update(@PathVariable Long id, @RequestBody Genre genre) {
        Genre g;

        try {
            g = genreRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        g.setNom(genre.getNom());
        g = genreRepository.save(g);
        return ResponseEntity.ok(g);
    }

    @DeleteMapping("/genres/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            genreRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok().build();
    }
}