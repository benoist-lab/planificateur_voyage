package com.planificateurVoyage.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.planificateurVoyage.model.Voyage;
import com.planificateurVoyage.tools.JPASpecificationUtility;

public interface VoyageRepository extends JpaRepository <Voyage,Long>, JpaSpecificationExecutor {
	
	public static Specification hasVoyage (Voyage voyageCritere) {
		Specification specification=null;
		
		if (voyageCritere.getLibelle()!=null) {
			specification=JPASpecificationUtility.andToSpecification(specification, (voyage,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(voyage.get("libelle"), voyageCritere.getLibelle()));
		}
		
		// ... ???

		return specification;		
	}

}
