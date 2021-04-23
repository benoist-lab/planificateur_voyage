package com.planificateurVoyage.model;


import java.io.Serializable;
//import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PersonneVoyageKey implements Serializable {

	@Column(name = "id_personne")
	private Long personneId;
	
	@Column(name = "id_voyage")
	private Long voyageId;
	
	
	
	
	
}
