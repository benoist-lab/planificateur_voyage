package com.planificateurVoyage.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.tools.JPASpecificationUtility;


public interface CodePostalRepository extends JpaRepository <CodePostal,Long>, JpaSpecificationExecutor {

	public static Specification hasCodePostal (CodePostal codePostalCritere) {
		Specification specification=null;

		if ((codePostalCritere.getCodePostal()!=null) && (!codePostalCritere.getCodePostal().isEmpty())){
			specification=JPASpecificationUtility.andToSpecification(specification, (codePostal,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal(codePostal.get("codePostal"), codePostalCritere.getCodePostal()));
		}
		
		if ((codePostalCritere.getVille()!=null) && (!codePostalCritere.getVille().isEmpty())) {
			specification=JPASpecificationUtility.andToSpecification(specification, (codePostal,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal(codePostal.get("ville"),codePostalCritere.getVille()));
		}
		
		if ((codePostalCritere.getPays()!=null) && (codePostalCritere.getPays().getId()!=null)){
			specification=JPASpecificationUtility.andToSpecification(specification, (codePostal,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal(codePostal.get("pays").get("id"), codePostalCritere.getPays().getId()));
		}
		
		return specification;
	}
	
	public static Specification byCodePostal (String codePostalCritere) {
		Specification specification=null;
		
		specification=JPASpecificationUtility.andToSpecification(specification, (codePostal,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal(codePostal.get("codePostal"), codePostalCritere));
		
		return specification;
	}
	
}
