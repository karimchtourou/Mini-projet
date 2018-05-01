package com.example.entities;


import java.io.Serializable;

public class PrioriteID implements Serializable{
	
	private Long id_demande;
	private Long id_sejour;
	
	public PrioriteID() {
		super();
	}

	public PrioriteID(Long id_demande, Long id_sejour) {
		super();
		this.id_demande = id_demande;
		this.id_sejour = id_sejour;
	}

	public Long getId_demande() {
		return id_demande;
	}

	public void setId_demande(Long id_demande) {
		this.id_demande = id_demande;
	}

	public Long getId_sejour() {
		return id_sejour;
	}

	public void setId_sejour(Long id_sejour) {
		this.id_sejour = id_sejour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_demande == null) ? 0 : id_demande.hashCode());
		result = prime * result + ((id_sejour == null) ? 0 : id_sejour.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrioriteID other = (PrioriteID) obj;
		if (id_demande == null) {
			if (other.id_demande != null)
				return false;
		} else if (!id_demande.equals(other.id_demande))
			return false;
		if (id_sejour == null) {
			if (other.id_sejour != null)
				return false;
		} else if (!id_sejour.equals(other.id_sejour))
			return false;
		return true;
	}
	
	
	

}
