/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocacola.inklog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cocacola.inklog.model.Webcomic;
import com.cocacola.inklog.model.enums.StatutLecture;

/**
 *
 * @author tetra
 */
public interface WebcomicRepository extends CrudRepository<Webcomic, Long> {
    List<Webcomic> findAllByOrderByLectureSuiviNoteDesc();

    List<Webcomic> findAllByOrderByDateAjoutDesc();

    Integer countByStatut(StatutLecture statut);

    @Query("SELECT AVG(ls.note) FROM Webcomic w JOIN w.lectureSuivi ls")
    Double getAverageNote();

    @Query("SELECT w.genres FROM Webcomic w GROUP BY w.genres ORDER BY COUNT(w) DESC LIMIT 1")
    String getGenrePrefere();

    @Query("SELECT w.type FROM Webcomic w GROUP BY w.type ORDER BY COUNT(w) DESC LIMIT 1")
    String getTypePrefere();

    @Query("SELECT SUM(ls.chapitreActuel) FROM Webcomic w JOIN w.lectureSuivi ls")
    Long getTotalChapitresLus();

    List<Webcomic> findByStatut(StatutLecture statut);

    List<Webcomic> findByType(String type);

    List<Webcomic> findByGenres_Id(Long genreId);

    List<Webcomic> findByAuteur_Id(Long auteurId);

    List<Webcomic> findByTitreContainingIgnoreCase(String titre);
}
