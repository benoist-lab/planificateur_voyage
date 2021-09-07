package com.planificateurVoyage.model.request;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.planificateurVoyage.model.ActiviteVoyage;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.PersonneVoyage;
import com.planificateurVoyage.model.StatutVoyage;

import lombok.Data;

@Data
public class VoyageCreateRequest {
	
	private Long id;	
    private String libelle;    
    private String description;    
    private StatutVoyage statut;
    
    List<Personne> personnes=new LinkedList ();
    
    List<ActiviteVoyage> activites=new LinkedList ();
	
}
