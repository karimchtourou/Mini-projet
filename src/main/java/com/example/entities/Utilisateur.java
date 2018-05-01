package com.example.entities;

import java.io.Serializable;

import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ROLE", discriminatorType = DiscriminatorType.STRING, length = 50)
public abstract class  Utilisateur implements Serializable {

	@Id
	private String login ;
	private String mot_pass ;
	private String nom ;
	private String prenom ;
	private String tel ;
	private String email ;

	
	@ManyToOne
	@JoinColumn(name = "ADR_USER")
	private Adresse adresse ;
	
	@OneToMany(mappedBy="utilisateur")
	private Collection<Enfant> enfants ;
	
	public Utilisateur() {
		super();
	}
	
	
	
	public Utilisateur(String login, String mot_pass, String nom, String prenom, String tel, String email,
			Adresse adresse, Collection<Enfant> enfants) {
		super();
		this.login = login;
		this.mot_pass = mot_pass;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.adresse = adresse;
		this.enfants = enfants;
		
	}



	public Utilisateur(String login, String mot_pass, String nom, String prenom, String tel, String email,
			Adresse adresse) {
		super();
		this.login = login;
		this.mot_pass = mot_pass;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.adresse = adresse;
		
	}


	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMot_pass() {
		return mot_pass;
	}
	public void setMot_pass(String mot_pass) {
		this.mot_pass = mot_pass;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}



	public Collection<Enfant> getEnfants() {
		return enfants;
	}

	

	@Override
	public String toString() {
		return "Utilisateur [login=" + login + ", mot_pass=" + mot_pass + ", nom=" + nom + ", prenom=" + prenom
				+ ", tel=" + tel + ", email=" + email +  ", adresse=" + adresse + ", enfants="
				+ enfants + "]";
	}



	public void setEnfants(Collection<Enfant> enfants) {
		this.enfants = enfants;
	}

	
	
}