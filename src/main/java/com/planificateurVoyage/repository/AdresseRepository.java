package com.planificateurVoyage.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.tools.JPASpecificationUtility;

public interface AdresseRepository extends JpaRepository <Adresse, Long>, JpaSpecificationExecutor {

	public static Specification hasAdresse(Adresse adresseCritere) {
		Specification specification=null;
		
		if (adresseCritere.getLibelle()!=null) {
			specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(adresse.get("libelle"), adresseCritere.getLibelle()));
		}
		
		if (adresseCritere.getLigne1()!=null) {
			specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(adresse.get("ligne1"), adresseCritere.getLigne1()));
		}
		
		if (adresseCritere.getLigne2()!=null) {
			specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(adresse.get("ligne2"), adresseCritere.getLigne2()));
		}

		if (adresseCritere.getLigne3()!=null) {
			specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(adresse.get("ligne3"), adresseCritere.getLigne3()));
		}
		
		if ((adresseCritere.getCodePostal()!=null) && (adresseCritere.getCodePostal().getId()!=null)) {
			specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.equal(adresse.get("codePostal").get("id"), adresseCritere.getCodePostal().getId()));
		}

		return specification;
	}
	
	public static Specification hasAdresseLike(Adresse adresseCritere) {
		Specification specification=null;
		
		if ((adresseCritere.getLibelle()!=null) && (!adresseCritere.getLibelle().isEmpty())) {
			specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.like(adresse.get("libelle"), "%"+adresseCritere.getLibelle()+"%"));
		}
		
		if ((adresseCritere.getLigne1()!=null) && (!adresseCritere.getLigne1().isEmpty())) {
			specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.like(adresse.get("ligne1"), "%"+adresseCritere.getLigne1()+"%"));
		}
		
		if ((adresseCritere.getLigne2()!=null) && (!adresseCritere.getLigne2().isEmpty())) {
			specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.like(adresse.get("ligne2"), "%"+adresseCritere.getLigne2()+"%"));
		}

		if ((adresseCritere.getLigne3()!=null) && (!adresseCritere.getLigne3().isEmpty())) {
			specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.like(adresse.get("ligne3"), "%"+adresseCritere.getLigne3()+"%"));
		}

		//critere sur les codes postaux
		
		if (adresseCritere.getCodePostal()!=null) {
			if ((adresseCritere.getCodePostal().getCodePostal()!=null) && (!adresseCritere.getCodePostal().getCodePostal().isEmpty())) {
				specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.like(adresse.get("codePostal").get("codePostal"), "%"+adresseCritere.getCodePostal().getCodePostal()+"%"));
			}
			
			if ((adresseCritere.getCodePostal().getVille()!=null) && (!adresseCritere.getCodePostal().getVille().isEmpty())) {
				specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.like(adresse.get("codePostal").get("ville"), "%"+adresseCritere.getCodePostal().getVille()+"%"));
			}
			
			// critere sur le pays
			if (adresseCritere.getCodePostal().getPays()!=null) {
				if ((adresseCritere.getCodePostal().getPays().getPays()!=null) && (!adresseCritere.getCodePostal().getPays().getPays().isEmpty())) {
					specification=JPASpecificationUtility.andToSpecification(specification, (adresse,criteriaQuery,criteriaBuilder)->criteriaBuilder.like(adresse.get("codePostal").get("pays").get("pays"), "%"+adresseCritere.getCodePostal().getPays().getPays().toUpperCase()+"%"));
				}
			}
			
		}
		
		return specification;
	}
}
