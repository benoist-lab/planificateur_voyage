package com.planificateurVoyage.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.planificateurVoyage.model.StatutVoyage;
import com.planificateurVoyage.tools.JPASpecificationUtility;

public interface StatutVoyageRepository extends JpaRepository <StatutVoyage, Long>, JpaSpecificationExecutor {

	public static Specification hasStatutVoyage (StatutVoyage statutVoyageCritere) {
		Specification specification=null;
		
		if ((statutVoyageCritere.getLibelle()!=null) && (!statutVoyageCritere.getLibelle().isEmpty())) {
			specification=JPASpecificationUtility.andToSpecification (specification, (statutVoyage,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal (statutVoyage.get("libelle"),statutVoyageCritere.getLibelle()));
		}
		
		return specification;
	}
	
}
