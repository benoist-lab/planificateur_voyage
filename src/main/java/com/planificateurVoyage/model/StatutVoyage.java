package com.planificateurVoyage.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="R_STATUT_VOYAGE")
public class StatutVoyage implements IDataModel {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name="id_statut")
	private Long id;
	
	@Column(name="libelle")
	private String libelle;
	
	@OneToMany (mappedBy="statut")
	@JsonBackReference(value="statutvoyage-voyage")
	private Set <Voyage> voyage;

	@Override
	public String toString () {
		return getLibelle ();
	}
}
