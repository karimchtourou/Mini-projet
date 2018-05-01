package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Demande implements Serializable {

	@Id
	@GeneratedValue
	private Long id_Dem;

	private Date date;
	private String ville_dep;
	private String etat;
	
	@OneToMany(mappedBy="demande")
	private Collection<Priorite> priorites ;
	@ManyToOne
	@JoinColumn(name = "id_enfant")
	private Enfant enfant ;

	public Demande() {
		super();
	}

	public Demande(Date date, String ville_dep, String etat) {
		super();
		this.date = date;
		this.ville_dep = ville_dep;
		this.etat = etat;

	}
	

	public Demande( Date date, String ville_dep, String etat, Enfant enfant) {
		super();
		this.date = date;
		this.ville_dep = ville_dep;
		this.etat = etat;
		this.enfant = enfant;
	}

	public Long getId_Dem() {
		return id_Dem;
	}

	public void setId_Dem(Long id_Dem) {
		this.id_Dem = id_Dem;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getVille_dep() {
		return ville_dep;
	}

	public void setVille_dep(String ville_dep) {
		this.ville_dep = ville_dep;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
