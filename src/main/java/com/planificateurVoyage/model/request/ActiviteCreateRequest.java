package com.planificateurVoyage.model.request;

import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.Categorie;

import lombok.Data;

@Data
public class ActiviteCreateRequest {
	
	private Long id;	
	private String libelle;	
	private double cout;	
	private boolean forfaitaire=false;	
	private String description;
	private Categorie categorie;
	private Adresse adresse;
	
	
}
