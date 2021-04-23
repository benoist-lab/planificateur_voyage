package com.planificateurVoyage.repository;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.tools.JPASpecificationUtility;

public interface CategorieRepository extends JpaRepository <Categorie, Long>, JpaSpecificationExecutor {
	
	public static Specification hasCategorie (Categorie categorieCritere) {
		Specification specification=null;
		
		if ((categorieCritere.getLibelle()!=null) && (!categorieCritere.getLibelle().isEmpty())) {
			specification=JPASpecificationUtility.andToSpecification(specification, (categorie, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal (categorie.get("libelle"),categorieCritere.getLibelle()));
		}
		
		return specification;
	}
	

}
