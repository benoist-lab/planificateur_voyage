package com.planificateurVoyage.tools;

import org.springframework.data.jpa.domain.Specification;

public class JPASpecificationUtility {
	
	public static Specification andToSpecification (Specification specification,Specification newSpecification) {
		Specification spec=null;
		
		if (specification==null) {
			spec=Specification.where(newSpecification);
		}else {
			spec=specification.and(newSpecification);
		}
		
		return spec;
	}

	public static Specification orToSpecification (Specification specification,Specification newSpecification) {
		Specification spec=null;
		
		if (specification==null) {
			spec=Specification.where(newSpecification);
		}else {
			spec=specification.or(newSpecification);
		}
		
		return spec;
	}
}
