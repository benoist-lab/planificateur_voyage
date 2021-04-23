package com.planificateurVoyage.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.planificateurVoyage.model.PersonneVoyage;

public class HasPersonneVoyagePersonneIdSpec {

	public static Specification<PersonneVoyage> equals (Long id){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("id").get("PersonneId"), id);
        };
	}
	
}
