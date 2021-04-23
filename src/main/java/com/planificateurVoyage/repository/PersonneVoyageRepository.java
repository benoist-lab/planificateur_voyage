package com.planificateurVoyage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.PersonneVoyage;
import com.planificateurVoyage.model.PersonneVoyageKey;

public interface PersonneVoyageRepository extends JpaRepository <PersonneVoyage,PersonneVoyageKey>, JpaSpecificationExecutor<PersonneVoyage> {

	public List<PersonneVoyage> findByVoyageId (Long id);
	public List<PersonneVoyage> findByPersonneId (Long id);
	
}
