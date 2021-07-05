package com.planificateurVoyage.model;

import java.util.Date;

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
public class ActiviteVoyage {
	
	@Column (name="id")
	@EmbeddedId
	private ActiviteVoyageKey activiteVoyageKey;
	
	@ManyToOne
	@MapsId ("ActiviteId")
	@JoinColumn (name="id_activite")
	@JsonBackReference (value="activiteVoyage-activite")
	private Activite activite;
	
	@ManyToOne
	@MapsId ("VoyageId")
	@JoinColumn  (name="id_voyage")
	@JsonBackReference (value="activiteVoyage-voyage")
	private Voyage voyage;
	
	@Column(name="date_debut")
	private Date dateDebut;
	
	@Column (name="date_fin")
	private Date dateFin;
	
	

}
