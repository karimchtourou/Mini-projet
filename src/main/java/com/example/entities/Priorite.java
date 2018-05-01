package com.example.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Priorite implements Serializable {

	@EmbeddedId
	private PrioriteID prioriteID ;
	
	private int rang;
	@ManyToOne
	@JoinColumn(name = "id_demande" , referencedColumnName = "id_Dem", insertable = false, updatable = false)
	private Demande demande;

	
	@ManyToOne
	@JoinColumn(name = "id_sejour" ,  referencedColumnName = "reference", insertable = false, updatable = false)
	private Sejour sejour;

	public Priorite() {
		super();
	}
	

	public Priorite(int rang, Demande demande, Sejour sejour) {
		super();
		this.prioriteID=new PrioriteID(demande.getId_Dem(), sejour.getReference());
		this.rang = rang;
		this.demande = demande;
		this.sejour = sejour;
	}


	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public Priorite(int rang) {
		super();
		this.rang = rang;
	}

}
