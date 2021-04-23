package com.planificateurVoyage.model.request;

import com.planificateurVoyage.model.Categorie;

import lombok.Data;

@Data
public class ActiviteRechercheRequest {

	private Categorie categorie;
	private String libelle;
	private String adresse;
	
}
