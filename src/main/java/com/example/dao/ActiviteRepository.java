package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Activite;

public interface ActiviteRepository extends JpaRepository<Activite, Long>{

}
