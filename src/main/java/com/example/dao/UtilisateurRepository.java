package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{

	@Query("select o.class from Utilisateur o where o.login=:x")
	public String getUserRole(@Param("x") String login);

}
