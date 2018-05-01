package com.example.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Sejour;

public interface SejourRepository extends JpaRepository<Sejour, Long> {
	@Query("select o from Sejour o where TO_DAYS(o.dateDeb) - TO_DAYS (:x) >= 7  and o.nbplaces < o.nbPlacesMax")
	public List<Sejour> listSejour(@Param("x") Date date);

}
