package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Demande;

public interface DemandeRepository extends JpaRepository<Demande, Long>{

}
