package com.planificateurVoyage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="F_ACTIVITE")
public class Activite implements IDataModel {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name="id_activite")
	private Long id;
	
	private String libelle;
	
	private double cout=0;
	
	private boolean forfaitaire=false;
	
	private String description;
	
	
	// Categorie
	@ManyToOne
	@JoinColumn (name="id_categorie")
	private Categorie categorie;
	
	//Adresse
	@ManyToOne
	@JoinColumn (name="id_adresse")
	private Adresse adresse;

}
