package com.planificateurVoyage.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.request.ActiviteCreateRequest;
import com.planificateurVoyage.model.request.ActiviteRechercheRequest;
import com.planificateurVoyage.repository.ActiviteRepository;
import com.planificateurVoyage.repository.AdresseRepository;
import com.planificateurVoyage.tools.JPASpecificationUtility;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.tools.exception.ActiviteInvalidException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActiviteService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final ActiviteRepository activiteRepository;
	private final CategorieService categorieService;
	private final AdresseService adresseService;
	
	public Activite readActivite (Long id) {
		Optional <Activite> activite=activiteRepository.findById(id);
		
		if (activite.isPresent()) {
			return activite.get();
		}
		
		throw new EntityNotFoundException ("ne trouve pas l'activite ID="+id);
	}
	
	
	public List<Activite> readActivites () {
		return activiteRepository.findAll();
	}
	
	public Activite createActivite (ActiviteCreateRequest request) {
		
		Activite activiteCritere=new Activite ();
		BeanUtils.copyProperties(request, activiteCritere);
		
		try {
			categorieService.readCategorie(request.getCategorie().getId());
		}catch (EntityNotFoundException enfe) {
			throw new EntityExistsException ("la cat\u00E9gorie de l'activit\u00E9 n'existe pas");
		}
		
		try {
			adresseService.readAdresse(request.getAdresse().getId());
		}catch (EntityNotFoundException enfe) {
			throw new EntityExistsException ("l'adresse de l'activit\u00E9 n'existe pas");
		}
		
		if (request.getId()==null) {
			if (isActiviteExiste (activiteCritere)) {
				throw new EntityExistsException ("l'activit\u00E9 existe");
			}
		}
		else {
			if (readActivite (request.getId())==null) {
				throw new EntityExistsException ("l'activit\u00E9 n'existe pas");
			}
		}

		
		return insererActivite (request);
	}
	
	/*
	 * @throws ActiviteInvalidException, AdresseInvalidException, CodePostalInvalidException, VilleInvalidException, PaysInvalidException, EntityNotFoundException
	 */
	public void validerActivite (Activite activite) {
		
//		if ((activite.getLibelle().isEmpty())
//				|| (activite.getDescription().isEmpty())
//				) {
		if (activite.getLibelle().isEmpty()) {
			throw new ActiviteInvalidException ("l'activit\u00E9 est imcomplete");
		}
		
		ToolRegex.valideNumerique(Double.toString(activite.getCout()), "cout");
		
		categorieService.readCategorie(activite.getCategorie().getId());
		adresseService.validerAdresse(activite.getAdresse());
		
	}
	
	@Transactional
	private Activite insererActivite (ActiviteCreateRequest request) {
		
		Activite activiteToCreate=new Activite ();
		
		BeanUtils.copyProperties(request, activiteToCreate);
		
		validerActivite (activiteToCreate);
		
		return activiteRepository.save(activiteToCreate);
		
	}
	

	public boolean isActiviteExiste (Activite activiteCritere) {
		return activiteRepository.findAll(ActiviteRepository.hasActivite (activiteCritere)).size()>0;
	}
	

	public List<Activite> rechercherActivite (ActiviteRechercheRequest activiteCritere) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Activite> criteriaQuery = criteriaBuilder.createQuery(Activite.class);
		Root<Activite> root = criteriaQuery.from(Activite.class);
		
		Predicate predicateLibelle=null;
		Predicate predicateCategorie=null;
		Predicate predicateAdresseFinal=null;		
		
		if ((activiteCritere.getLibelle()!=null) && (!activiteCritere.getLibelle().isEmpty())) {
			predicateLibelle=criteriaBuilder.like(root.get("libelle"), "%"+activiteCritere.getLibelle()+"%");
		}
		
			
		if ((activiteCritere.getCategorie()!=null) && (activiteCritere.getCategorie().getId()!=null) && (activiteCritere.getCategorie().getId()!=0)) {
			predicateCategorie=criteriaBuilder.equal(root.get("categorie").get("id"), activiteCritere.getCategorie().getId());
		}

		
		if ((activiteCritere.getAdresse()!=null) && (!activiteCritere.getAdresse().isEmpty())) {
			Predicate predicateAdresseLibelle= criteriaBuilder.like(root.get("adresse").get("libelle"), "%"+activiteCritere.getAdresse()+"%");
			Predicate predicateAdresseLigne1= criteriaBuilder.like(root.get("adresse").get("ligne1"), "%"+activiteCritere.getAdresse()+"%");
			Predicate predicateAdresseLigne2= criteriaBuilder.like(root.get("adresse").get("ligne2"), "%"+activiteCritere.getAdresse()+"%");
			Predicate predicateAdresseLigne3= criteriaBuilder.like(root.get("adresse").get("ligne3"), "%"+activiteCritere.getAdresse()+"%");
			Predicate predicateAdresseCodePostal= criteriaBuilder.like(root.get("adresse").get("codePostal").get("codePostal"), "%"+activiteCritere.getAdresse()+"%");
			Predicate predicateAdresseVille= criteriaBuilder.like(root.get("adresse").get("codePostal").get("ville"), "%"+activiteCritere.getAdresse()+"%");
			Predicate predicateAdressePays= criteriaBuilder.like(root.get("adresse").get("codePostal").get("pays").get("pays"), "%"+activiteCritere.getAdresse()+"%");
			
			predicateAdresseFinal=criteriaBuilder.or(predicateAdresseLibelle,
					predicateAdresseLigne1,predicateAdresseLigne2,predicateAdresseLigne3,
					predicateAdresseCodePostal,predicateAdresseVille,predicateAdressePays
					);

		}

		// construction de la condition
		Predicate finalPredicate = null;
		
		if (predicateLibelle!=null) {
			finalPredicate=predicateLibelle;
		}
		
		if (predicateCategorie!=null) {
			if (finalPredicate==null) {
				finalPredicate=predicateCategorie;
			}else {
				finalPredicate=criteriaBuilder.and(finalPredicate, predicateCategorie);
			}
		}
		
		if (predicateAdresseFinal!=null) {
			if (finalPredicate==null) {
				finalPredicate=predicateAdresseFinal;
			}else {
				finalPredicate=criteriaBuilder.and(finalPredicate, predicateAdresseFinal);
			}
		}

		//resultat
		if (finalPredicate==null) {
			return activiteRepository.findAll();
		}
		
		criteriaQuery.where(finalPredicate);
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}


}
