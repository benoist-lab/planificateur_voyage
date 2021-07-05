package com.planificateurVoyage.model.request;

import lombok.Data;

@Data
public class PersonneVoyageRemoveRequest {
	
	private long personneId;
	private long voyageId;
	
	
}
