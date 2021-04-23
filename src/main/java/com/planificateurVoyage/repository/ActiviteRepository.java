package com.planificateurVoyage.repository;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.request.ActiviteRechercheRequest;
import com.planificateurVoyage.tools.JPASpecificationUtility;

public interface ActiviteRepository extends JpaRepository <Activite, Long>, JpaSpecificationExecutor  {

	public static Specification hasActivite(Activite activiteCritere) {
		Specification specification=null;
		
		if (activiteCritere.getLibelle()!=null) {
			specification=JPASpecificationUtility.andToSpecification(specification, (activite,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(activite.get("libelle"), activiteCritere.getLibelle()));
		}
		
		specification=JPASpecificationUtility.andToSpecification(specification, (activite,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(activite.get("cout"), activiteCritere.getCout()));
		
		specification=JPASpecificationUtility.andToSpecification(specification, (activite,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(activite.get("forfaitaire"), activiteCritere.isForfaitaire()));
			
		if (activiteCritere.getDescription()!=null) {
			specification=JPASpecificationUtility.andToSpecification(specification, (activite,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(activite.get("description"), activiteCritere.getDescription()));
		}
			
		if ((activiteCritere.getCategorie()!=null) && (activiteCritere.getCategorie().getId()!=null)) {
			specification=JPASpecificationUtility.andToSpecification(specification, (activite,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(activite.get("categorie").get("id"), activiteCritere.getCategorie().getId()));
		}
		
		if ((activiteCritere.getAdresse()!=null) && (activiteCritere.getAdresse().getId()!=null)) {
			specification=JPASpecificationUtility.andToSpecification(specification, (activite,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(activite.get("adresse").get("id"), activiteCritere.getAdresse().getId()));
		}

		return specification;
	}
	
	
}
