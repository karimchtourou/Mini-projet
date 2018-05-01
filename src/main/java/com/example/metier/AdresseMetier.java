package com.example.metier;

import com.example.entities.Adresse;

public interface AdresseMetier {

	Adresse consulterAdresse(long numero);

	void ajoutAdr(Adresse adr);

}
