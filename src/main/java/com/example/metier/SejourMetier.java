package com.example.metier;


import java.util.Collection;
import java.util.Date;

import com.example.entities.Activite;
import com.example.entities.Adresse;

public interface SejourMetier {
	
	void ajoutSejour(Date dateDeb, Date dateFin, String description, String lieu, double prix, int nbplaces,
			int nbPlacesMax, Collection<Activite> activites);
}
