package com.example.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Activite implements Serializable{

	@Id @GeneratedValue
	private Long id_activite ;
	private String description ; 
	private String libelle ;
	
	@ManyToMany(mappedBy="activites")
	private Collection<Sejour> sejours ;
	
	public Activite() {
		super();
	}
	public Activite( String description, String libelle) {
		super();
		
		this.description = description;
		this.libelle = libelle;
	}
	public Long getId_activite() {
		return id_activite;
	}
	public void setId_activite(Long id_activite) {
		this.id_activite = id_activite;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Collection<Sejour> getSejours() {
		return sejours;
	}
	public void setSejours(Collection<Sejour> sejours) {
		this.sejours = sejours;
	} 
	
}
