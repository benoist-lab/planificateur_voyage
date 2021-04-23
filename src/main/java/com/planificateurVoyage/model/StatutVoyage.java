package com.planificateurVoyage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="R_STATUT_VOYAGE")
public class StatutVoyage {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name="id_statut")
	private Long id;
	
	@Column(name="libelle")
	private String libelle;

	@Override
	public String toString () {
		return getLibelle ();
	}
}
