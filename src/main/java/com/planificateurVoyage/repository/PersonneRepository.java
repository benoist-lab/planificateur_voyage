package com.planificateurVoyage.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.tools.JPASpecificationUtility;

public interface PersonneRepository extends JpaRepository<Personne, Long>, JpaSpecificationExecutor  {


	public static Specification hasPersonne (Personne personneCritere) {
		Specification specification=null;
		
		if (personneCritere.getNom()!=null && !personneCritere.getNom().isEmpty()) {
			specification=JPASpecificationUtility.andToSpecification (specification,(personne, criteriaQuery , criteriaBuilder ) -> criteriaBuilder.equal(personne.get("nom"), personneCritere.getNom()));
		}
		
		if (personneCritere.getPrenom()!=null && !personneCritere.getPrenom().isEmpty()) {
			specification=JPASpecificationUtility.andToSpecification (specification,(personne, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(personne.get("prenom"),  personneCritere.getPrenom()));
		}
		
		return specification;
	}


	public static Specification hasPersonneLike (Personne personneCritere) {
		Specification specification=null;
		
		if (personneCritere.getNom()!=null && !personneCritere.getNom().isEmpty()) {
			specification=JPASpecificationUtility.andToSpecification (specification,(personne, criteriaQuery , criteriaBuilder ) -> criteriaBuilder.like(personne.get("nom"), "%"+personneCritere.getNom()+"%"));
		}
		
		if (personneCritere.getPrenom()!=null && !personneCritere.getPrenom().isEmpty()) {
			specification=JPASpecificationUtility.andToSpecification (specification,(personne, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(personne.get("prenom"),  "%"+personneCritere.getPrenom()+"%"));
		}
		
		return specification;
	}

	
	
}
