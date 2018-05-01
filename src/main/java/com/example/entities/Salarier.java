package com.example.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
@DiscriminatorValue("SALARIER")
public class Salarier extends Utilisateur implements Serializable {

	
	private String matricule ;

	
	public Salarier() {
		super();
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public Salarier(String login, String mot_pass, String nom, String prenom, String tel, String email, Adresse adresse,
			Collection<Enfant> enfants, String matricule) {
		super(login, mot_pass, nom, prenom, tel, email, adresse, enfants);
		this.matricule = matricule;
	}
	public Salarier(String login, String mot_pass, String nom, String prenom, String tel, String email, Adresse adresse,
			String matricule) {
		super(login, mot_pass, nom, prenom, tel, email, adresse);
		this.matricule = matricule;
	}
	
	
	
	
	
	
}
