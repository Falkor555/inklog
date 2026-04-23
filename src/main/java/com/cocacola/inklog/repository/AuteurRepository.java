package com.cocacola.inklog.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cocacola.inklog.model.Auteur;

public interface AuteurRepository extends CrudRepository<Auteur, Long> {

    Optional<Auteur> findById(Long id);

}
