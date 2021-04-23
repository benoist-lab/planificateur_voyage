package com.planificateurVoyage.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.tools.JPASpecificationUtility;


public interface PaysRepository extends JpaRepository<Pays, Long>, JpaSpecificationExecutor {

	public static Specification hasPays (Pays paysCritere) {
		Specification specification=null;
		
		if (paysCritere.getPays()!=null && !paysCritere.getPays().isEmpty()) {
			specification=JPASpecificationUtility.andToSpecification (specification,(pays, criteriaQuery , criteriaBuilder ) -> criteriaBuilder.equal(pays.get("pays"), paysCritere.getPays()));
		}
		
		return specification;
	}
	
}
