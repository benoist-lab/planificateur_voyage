package com.planificateurVoyage.model.request;

import lombok.Data;

@Data
public class ActiviteVoyageRemoveRequest {

	private long activiteId;
	private long voyageId;
	
}
