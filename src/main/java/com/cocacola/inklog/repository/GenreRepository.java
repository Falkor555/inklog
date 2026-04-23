package com.cocacola.inklog.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cocacola.inklog.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findById(Long id);

}
