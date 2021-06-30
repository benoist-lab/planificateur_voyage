package com.planificateurVoyage.model;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PersonneVoyage {

	@Column(name="id")
	@EmbeddedId
	private PersonneVoyageKey personneVoyageKey;
	
	
	@ManyToOne
	@MapsId ("PersonneId")
	@JoinColumn (name="id_personne")
    @JsonBackReference(value="personnevoyage-personne")
	private Personne personne;
	
	@ManyToOne
	@MapsId ("VoyageId")
	@JoinColumn (name="id_voyage")
    @JsonBackReference(value="personnevoyage-voyage")
	private Voyage voyage;
	
	
	
	
}
