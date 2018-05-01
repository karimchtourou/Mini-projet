package com.example.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AdresseRepository;
import com.example.entities.Adresse;
@Service
@Transactional
public class AdresseMetierImpl implements AdresseMetier{

	@Autowired
	private AdresseRepository adrRep;
	@Override
	public void ajoutAdr(Adresse adr) {
		
		adrRep.save(adr);
	}
	@Override
	public Adresse consulterAdresse(long numero) {
		Adresse adr = adrRep.findOne(numero);
		if (adr == null)
			throw new RuntimeException("adresse introuvable");
		return adr;
	}


}
