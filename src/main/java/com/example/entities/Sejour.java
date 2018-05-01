package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Sejour implements Serializable {

	@Id
	@GeneratedValue
	private long reference;
	private Date dateDeb;
	private Date dateFin;
	private String description;
	private String lieu;
	private double prix;
	private int nbplaces;
	private int nbPlacesMax;

	@OneToMany(mappedBy = "sejour")
	private Collection<Priorite> priorites;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "Activite_Sejour", joinColumns = @JoinColumn(name = "id_sejour", referencedColumnName = "reference"), inverseJoinColumns = @JoinColumn(name = "id_activite", referencedColumnName = "id_activite"))
	private Collection<Activite> activites;

	public Sejour() {
		super();
	}

	public Sejour(Date dateDeb, Date dateFin, String description, String lieu, double prix, int nbplaces,
			int nbPlacesMax, Collection<Activite> activites) {
		super();
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.description = description;
		this.lieu = lieu;
		this.prix = prix;
		this.nbplaces = nbplaces;
		this.nbPlacesMax = nbPlacesMax;
		this.activites = activites;
	}

	public long getReference() {
		return reference;
	}

	public void setReference(long reference) {
		this.reference = reference;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getNbplaces() {
		return nbplaces;
	}

	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}

	public int getNbPlacesMax() {
		return nbPlacesMax;
	}

	public void setNbPlacesMax(int nbPlacesMax) {
		this.nbPlacesMax = nbPlacesMax;
	}

}
