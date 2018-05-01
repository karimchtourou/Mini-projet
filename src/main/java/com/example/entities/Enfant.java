package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Enfant implements Serializable {

	@Id @GeneratedValue
	private Long id ;
	private String nom ;
	private String prenom ;
	private Date date_naiss ;
	private String sexe ;
	@ManyToOne
	@JoinColumn(name = "User_PARENT")
	private Utilisateur utilisateur ;
	@OneToMany(mappedBy="enfant")
	private Collection<Demande> demandes ;
	
	public Enfant() {
		super();
	}

	public Enfant(String nom, String prenom, Date date_naiss, String sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.date_naiss = date_naiss;
		this.sexe = sexe;
	}

	public Enfant(String nom, String prenom, Date date_naiss, String sexe, Utilisateur utilisateur) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.date_naiss = date_naiss;
		this.sexe = sexe;
		this.utilisateur = utilisateur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDate_naiss() {
		return date_naiss;
	}

	public void setDate_naiss(Date date_naiss) {
		this.date_naiss = date_naiss;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	
	
	
}
