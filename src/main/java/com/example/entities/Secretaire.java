package com.example.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("SECRETAIRE")
public class Secretaire extends Utilisateur implements Serializable{
	
	
	private String matricule ;

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Secretaire() {
		super();
	}

	public Secretaire(String login, String mot_pass, String nom, String prenom, String tel, String email,
			Adresse adresse, String matricule) {
		super(login, mot_pass, nom, prenom, tel, email, adresse);
		this.matricule = matricule;
	}

	@Override
	public String toString() {
		return "Secretaire [matricule=" + matricule + ", getLogin()=" + getLogin() + ", getMotPass()=" + getMot_pass()
				+ ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getTel()=" + getTel() + ", getEmail()="
				+ getEmail() + ", getAdresse()=" + getAdresse() + ", getEnfants()=" + getEnfants() + ", toString()="
				+ super.toString() +  ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

	

	
	
	
	
}
