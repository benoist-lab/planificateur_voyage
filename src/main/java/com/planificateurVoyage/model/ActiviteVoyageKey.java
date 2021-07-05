package com.planificateurVoyage.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ActiviteVoyageKey implements Serializable {
	
	@Column (name="id_activite")
	private Long activiteId;
	
	@Column (name="id_voyage")
	private Long voyageId;
	
	

}
