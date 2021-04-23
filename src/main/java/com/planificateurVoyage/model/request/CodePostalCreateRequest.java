package com.planificateurVoyage.model.request;

import com.planificateurVoyage.model.Pays;

import lombok.Data;

@Data
public class CodePostalCreateRequest {
	
	private String codePostal;
	private String ville;
	private Pays pays;
	
}
