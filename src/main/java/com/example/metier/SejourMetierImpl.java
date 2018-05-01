package com.example.metier;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AdresseRepository;
import com.example.dao.SejourRepository;
import com.example.dao.UtilisateurRepository;
import com.example.entities.Activite;
import com.example.entities.Adresse;
import com.example.entities.Salarier;
import com.example.entities.Sejour;
import com.example.entities.Utilisateur;

@Service
@Transactional
public class SejourMetierImpl implements SejourMetier {
	@Autowired
	private UtilisateurRepository utilRep;
	@Autowired
	private SejourRepository sejourRep;

	@Override
	public void ajoutSejour(Date dateDeb, Date dateFin, String description, String lieu, double prix, int nbplaces,
			int nbPlacesMax, Collection<Activite> activites) {
		Sejour sej = new Sejour(dateDeb, dateFin, description, lieu, prix, nbplaces, nbPlacesMax, activites);
		sejourRep.save(sej);
	}

}
