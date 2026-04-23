/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocacola.inklog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cocacola.inklog.model.Webcomic;

/**
 *
 * @author tetra
 */
public interface WebcomicRepository extends JpaRepository<Webcomic, Long> {
}
