package com.cocacola.inklog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cocacola.inklog.model.LectureSuivi;
import com.cocacola.inklog.repository.LectureSuiviRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class LectureSuiviController {
    LectureSuiviRepository lectureSuiviRepository;

    @PostMapping("/suivis/")
    public ResponseEntity<LectureSuivi> createLectureSuivi(@RequestBody LectureSuivi suivis) {
        LectureSuivi suivi = new LectureSuivi();
        suivi.setChapitreActuel(suivis.getChapitreActuel());
        suivi.setNote(suivis.getNote());
        suivi.setAvis(suivis.getAvis());
        suivi.setDateDebutLecture(suivis.getDateDebutLecture());
        suivi.setDateDerniereLecture(suivis.getDateDerniereLecture());
        return ResponseEntity.ok(suivi);
    }

    @GetMapping("/suivis/")
    public ResponseEntity<LectureSuivi> getLectureSuivi() {
        Iterable<LectureSuivi> suivis = lectureSuiviRepository.findAll();
        return ResponseEntity.ok(suivis.iterator().next());
    }

    @PutMapping("suivis/{id}")
    public ResponseEntity<LectureSuivi> updateSuivis(@PathVariable Long id, @RequestBody LectureSuivi entity) {
        LectureSuivi foundSuivi = lectureSuiviRepository.findById(id).orElse(null);
        if (foundSuivi == null) {
            return ResponseEntity.notFound().build();
        }
        foundSuivi.setChapitreActuel(entity.getChapitreActuel());
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("suivis/{id}")
    public ResponseEntity<Void> deleteSuivis(@PathVariable Long id) {
        if (!lectureSuiviRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        lectureSuiviRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
