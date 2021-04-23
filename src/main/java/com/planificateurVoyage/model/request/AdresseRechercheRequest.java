package com.planificateurVoyage.model.request;

import com.planificateurVoyage.model.CodePostal;

import lombok.Data;

@Data
public class AdresseRechercheRequest {

	private String libelle;
	private String ligne1;
	private String ligne2;
	private String ligne3;
	private CodePostal codePostal;
	
}
