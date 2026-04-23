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
	public List<Webcomic> findAll() {
		return webcomicRepository.findAll();
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

	private void validateBusinessRules(Webcomic webcomic) {
		if (webcomic.getStatut() == StatutLecture.EN_COURS && webcomic.getChaptotal() != null) {
			throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "chapitreTotal ne peut pas etre renseigne si le statut est EN_COURS");
		}
	}
}
