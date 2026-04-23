/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocacola.inklog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cocacola.inklog.model.Webcomic;
import com.cocacola.inklog.model.enums.StatutLecture;

/**
 *
 * @author tetra
 */
public interface WebcomicRepository extends CrudRepository<Webcomic, Long> {

    List<Webcomic> findByStatut(StatutLecture statut);

    List<Webcomic> findByType(String type);

    List<Webcomic> findByGenres_Id(Long genreId);

    List<Webcomic> findByAuteur_Id(Long auteurId);

    List<Webcomic> findByTitreContainingIgnoreCase(String titre);
}
