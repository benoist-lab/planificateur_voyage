package com.planificateurVoyage.model.request;

import java.util.Date;

import lombok.Data;

@Data
public class ActiviteVoyageAddRequest {

	private long activiteId;
	private long voyageId;
	private Date dateDebut;
	private Date dateFin;
	
	
}
