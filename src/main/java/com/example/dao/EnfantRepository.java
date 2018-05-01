package com.example.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Enfant;

public interface EnfantRepository extends JpaRepository<Enfant, Long> {
	@Query("select o from Enfant o where YEAR(:x) - YEAR(o.date_naiss)>8 and YEAR(:x) - YEAR(o.date_naiss)<16 and o.utilisateur.login=:y")
	public List<Enfant> listEnfant(@Param("x") Date date, @Param("y") String login);

}
