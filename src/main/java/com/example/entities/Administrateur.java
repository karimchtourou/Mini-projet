package com.example.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrateur extends Utilisateur implements Serializable {

	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrateur(String login, String mot_pass, String nom, String prenom, String tel, String email,
			Adresse adresse) {
		super(login, mot_pass, nom, prenom, tel, email, adresse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Administrateur [getLogin()=" + getLogin() + ", getMot_pass()=" + getMot_pass() + ", getNom()="
				+ getNom() + ", getPrenom()=" + getPrenom() + ", getTel()=" + getTel() + ", getEmail()=" + getEmail()
				+ ", getAdresse()=" + getAdresse() + ", getEnfants()=" + getEnfants() + ", toString()="
				+ super.toString() +  ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

	
	
}
