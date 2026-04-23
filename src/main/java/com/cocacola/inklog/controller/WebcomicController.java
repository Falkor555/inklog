/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocacola.inklog.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cocacola.inklog.model.Webcomic;
import com.cocacola.inklog.model.enums.StatutLecture;
import com.cocacola.inklog.repository.WebcomicRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tetra
 */

@RestController
@RequestMapping("/webcomics")
public class WebcomicController {
	record WebcomicStats(
    long totalWebcomics,
    long enCours,
    long termines,
    long enPause,
    long abandonnes,
    double noteMoyenne,
    long chapitresLusTotaux,
    String genrePrefere,
    String typePrefere
) {}

	private final WebcomicRepository webcomicRepository;

	public WebcomicController(WebcomicRepository webcomicRepository) {
		this.webcomicRepository = webcomicRepository;
	}

	@PostMapping
	public ResponseEntity<Webcomic> create(@RequestBody Webcomic webcomic) {
		validateBusinessRules(webcomic);
		webcomic.setId(null);
		webcomic.setDateAjout(null);

		Webcomic savedWebcomic = webcomicRepository.save(webcomic);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedWebcomic.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedWebcomic);
	}

	@GetMapping
	public List<Webcomic> getAll() {
		return (List<Webcomic>) webcomicRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Webcomic> findById(@PathVariable Long id) {
		return webcomicRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Webcomic> update(@PathVariable Long id, @RequestBody Webcomic webcomic) {
		return webcomicRepository.findById(id)
				.map(existingWebcomic -> {
					validateBusinessRules(webcomic);

					existingWebcomic.setTitre(webcomic.getTitre());
					existingWebcomic.setType(webcomic.getType());
					existingWebcomic.setStatut(webcomic.getStatut());
					existingWebcomic.setSynopsis(webcomic.getSynopsis());
					existingWebcomic.setChaptotal(webcomic.getChaptotal());
					existingWebcomic.setAuteur(webcomic.getAuteur());
					existingWebcomic.setGenres(webcomic.getGenres());

					return ResponseEntity.ok(webcomicRepository.save(existingWebcomic));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!webcomicRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		webcomicRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	// Changer le statut d’un webcomic
	@PutMapping("/{id}/statut")
	public ResponseEntity<Webcomic> changerStatut(@PathVariable Long id, @RequestBody StatutLecture statut) {
		return webcomicRepository.findById(id)
				.map(existingWebcomic -> {
					existingWebcomic.setStatut(statut);
					return ResponseEntity.ok(webcomicRepository.save(existingWebcomic));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Classement par note décroissante
	@GetMapping("/classement/")
	public List<Webcomic> classementParNote() {
		return webcomicRepository.findAllByOrderByLectureSuiviNoteDesc();
	}

	// Les 5 derniers webcomics consultés
	@GetMapping("/recents/")
	public List<Webcomic> cinqDerniersConsultes() {
		return webcomicRepository.findAllByOrderByDateAjoutDesc().stream().limit(5).toList();
	}

	// Résumé global de la bibliothèque
	@GetMapping("stats/resume/")
	public ResponseEntity<WebcomicStats> resumeGlobal() {
		WebcomicStats stats = new WebcomicStats(
            webcomicRepository.count(),
            webcomicRepository.countByStatut(StatutLecture.EN_COURS),
            webcomicRepository.countByStatut(StatutLecture.TERMINE),
            webcomicRepository.countByStatut(StatutLecture.EN_PAUSE),
            webcomicRepository.countByStatut(StatutLecture.ABANDONNE),
            webcomicRepository.getAverageNote() != null ? Math.round(webcomicRepository.getAverageNote() * 10.0) / 10.0 : 0.0,
            webcomicRepository.getTotalChapitresLus() != null ? webcomicRepository.getTotalChapitresLus() : 0,
            webcomicRepository.getGenrePrefere(),
            webcomicRepository.getTypePrefere()
        );
		return ResponseEntity.ok(stats);
	}
	private void validateBusinessRules(Webcomic webcomic) {
		if (webcomic.getStatut() == StatutLecture.EN_COURS && webcomic.getChaptotal() != null) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "chapitreTotal ne peut pas etre renseigne si le statut est EN_COURS");
		}
	}
}
