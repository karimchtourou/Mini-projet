package com.example.metier;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EnfantRepository;
import com.example.entities.Enfant;
import com.example.entities.Utilisateur;
@Service
@Transactional
public class EnfantMetierImpl implements EnfantMetier{
@Autowired
private EnfantRepository enfRep;
	@Override
	public void ajoutEnfant(Enfant enf) {
		
		enfRep.save(enf);
	}
	@Override
	public List<Enfant> findAll() {
		return enfRep.findAll();
		
	}	
}
