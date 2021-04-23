package com.planificateurVoyage.model.request;

import lombok.Data;

@Data
public class PersonneCreateRequest {
	
	private Long id;
	private String nom;
	private String prenom;

}
