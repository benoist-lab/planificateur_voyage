package com.planificateurVoyage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.ActiviteVoyage;
import com.planificateurVoyage.model.ActiviteVoyageKey;
import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.PersonneVoyage;
import com.planificateurVoyage.model.PersonneVoyageKey;
import com.planificateurVoyage.model.Voyage;
import com.planificateurVoyage.model.request.ActiviteVoyageAddRequest;
import com.planificateurVoyage.model.request.PersonneVoyageAddRequest;
import com.planificateurVoyage.model.request.PersonneVoyageRemoveRequest;
import com.planificateurVoyage.model.request.VoyageCreateRequest;
import com.planificateurVoyage.repository.ActiviteRepository;
import com.planificateurVoyage.repository.ActiviteVoyageRepository;
import com.planificateurVoyage.repository.AdresseRepository;
import com.planificateurVoyage.repository.PersonneRepository;
import com.planificateurVoyage.repository.PersonneVoyageRepository;
import com.planificateurVoyage.repository.VoyageRepository;
import com.planificateurVoyage.ui.MainFrame;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoyageService {

	private final VoyageRepository voyageRepository;
	
	private final StatutVoyageService statutVoyageService;
	
	private final PersonneRepository personneRepository;
	
	private final PersonneVoyageRepository personneVoyageRepository;
	
	private final ActiviteRepository activiteRepository;
	
	private final ActiviteVoyageRepository activiteVoyageRepository;

    private static final Logger logger = LoggerFactory.getLogger(VoyageService.class);

    
    public Voyage readVoyage (Long id) {
    	Optional<Voyage> voyage=voyageRepository.findById(id);
    	
    	if (voyage.isPresent()) {
    		return voyage.get();
    	}
    	
		throw new EntityNotFoundException ("ne trouve pas le voyage ID="+id);
    }
    
    public List<Voyage> readVoyages () {
    	return voyageRepository.findAll();
    }
    
    public List<PersonneVoyage> readVoyagePersonnes (Long id) {
    	return personneVoyageRepository.findByVoyageId(id);
    }
    
    public List<PersonneVoyage> readVoyageParPersonne (Long id) {
    	return personneVoyageRepository.findByPersonneId(id);
    }
    
    public List<Personne> readVoyagePersonnesInformation (Long id) {
    	List<Personne> listPersonne= new ArrayList<>();
    	
    	List<PersonneVoyage> listVoyagePersonne = personneVoyageRepository.findByVoyageId(id);
    	
    	for(PersonneVoyage pv:listVoyagePersonne) {
    		listPersonne.add(pv.getPersonne());
    	}
    	
    	return listPersonne;
    }
    
    // GERER LE TRANSACTIONNAL ////////////////////////
    public Voyage createVoyage (VoyageCreateRequest request) {

    	Voyage voyageCritere=new Voyage ();
    	BeanUtils.copyProperties(request, voyageCritere);
    	
    	try {
    		statutVoyageService.readStatutVoyage(request.getStatut().getId());
    	}catch (EntityNotFoundException enfe) {
			throw new EntityExistsException ("le statut du voyage n'existe pas");
		}
    	
		logger.info("createVoyage: "+voyageCritere.getId());
    	
    	if (voyageCritere.getId()==null) {
    		if (isVoyageExiste(voyageCritere)) {
    			throw new EntityExistsException ("le voyage existe");
    		}
    	}else {
    		if (readVoyage(request.getId())==null) {
    			throw new EntityExistsException ("le voyage n'existe pas");
    		}
    	}
    	
    	return insererVoyage (request);
    }
    
    
    
    
    
	@Transactional
	private Voyage insererVoyage (VoyageCreateRequest voyage) {
		
		logger.info("insererVoyage");

    	Voyage voyageToCreate = new Voyage ();
    	BeanUtils.copyProperties(voyage, voyageToCreate);
    	
    	voyageToCreate=voyageRepository.save(voyageToCreate);
		
		logger.info("   ID=: "+voyageToCreate.getId());
		
		
		//cas des personne ajouter/retirer au voyage
		if (voyage.getId()!=null) {
//			Map<Long,Personne> listPersonneeActuel=new HashMap<>();
//
//			for (PersonneVoyage personneVoyage:readVoyagePersonnes(voyage.getId())) {
//				listPersonneeActuel.put(personneVoyage.getPersonneVoyageKey().getPersonneId(),personneVoyage.getPersonne());
//			}
			List<Long> listPersonneeActuel=new LinkedList<>();
			for (PersonneVoyage personneVoyage:readVoyagePersonnes(voyage.getId())) {
				listPersonneeActuel.add(personneVoyage.getPersonneVoyageKey().getPersonneId());
			}
			
			List<Long> listPersonneeNouvelle=new LinkedList<>();
			for (Personne pers:voyage.getPersonne()) {
				listPersonneeNouvelle.add(pers.getId());
			}
			
			//recherche des personnes supprimiees
			for (Long id:listPersonneeActuel) {
				if (!listPersonneeNouvelle.contains(id)) {
					

					logger.info("   -remove personne: "+id);
					

					PersonneVoyageRemoveRequest request;
					
					request=new PersonneVoyageRemoveRequest();
					request.setPersonneId(id);
					request.setVoyageId(voyageToCreate.getId());
					
					removePersonneToVoyage (request);
					
				}
			}
		}

		//ajout/Mise a jour des personnes
		for (Personne pers:voyage.getPersonne()) {
	    		
    		logger.info("   -personne: "+pers.getId());

    		PersonneVoyageAddRequest personneVoyageRequest=new PersonneVoyageAddRequest ();
    		personneVoyageRequest.setVoyageId(voyageToCreate.getId());
    		personneVoyageRequest.setPersonneId(pers.getId());
	    		
    		PersonneVoyage personneVoyage=addPersonneToVoyage (personneVoyageRequest);
    		voyageToCreate.getPersonneVoyage().add(personneVoyage);

    		logger.info("      OK");
		}

    	
    	return voyageToCreate;
	}
    
    public PersonneVoyage addPersonneToVoyage (PersonneVoyageAddRequest request) {
    	
    	PersonneVoyageKey personneVoyageKey;
    	PersonneVoyage personneVoyage;
    	Optional<Personne> personne;
    	Optional<Voyage> voyage;
    	
    	// la personne
    	personne=personneRepository.findById(request.getPersonneId());
    	if (!personne.isPresent()) {
    		throw new EntityNotFoundException("la personne "+request.getPersonneId()+" n'existe pas");
    	}
    	
    	// le voyage
    	voyage=voyageRepository.findById(request.getVoyageId());
    	if (!voyage.isPresent()) {
    		throw new EntityNotFoundException ("le voyage "+request.getVoyageId()+" n'existe pas");
    	}
    	
    	// la cle
    	personneVoyageKey=new PersonneVoyageKey ();    	
    	personneVoyageKey.setPersonneId(request.getPersonneId());
    	personneVoyageKey.setVoyageId(request.getVoyageId());
    	
    	// la personneVoyage
    	personneVoyage=new PersonneVoyage ();
    	personneVoyage.setPersonneVoyageKey(personneVoyageKey);
    	
    	return personneVoyageRepository.save(personneVoyage);
    }
    
    public PersonneVoyage removePersonneToVoyage (PersonneVoyageRemoveRequest request) {
    	
    	PersonneVoyageKey personneVoyageKey;
    	PersonneVoyage personneVoyage;
    	Optional<Personne> personne;
    	Optional<Voyage> voyage;
    	
    	// la personne
    	personne=personneRepository.findById(request.getPersonneId());
    	if (!personne.isPresent()) {
    		throw new EntityNotFoundException("la personne "+request.getPersonneId()+" n'existe pas");
    	}
    	
    	// le voyage
    	voyage=voyageRepository.findById(request.getVoyageId());
    	if (!voyage.isPresent()) {
    		throw new EntityNotFoundException ("le voyage "+request.getVoyageId()+" n'existe pas");
    	}
    	
    	// la cle
    	personneVoyageKey=new PersonneVoyageKey ();    	
    	personneVoyageKey.setPersonneId(request.getPersonneId());
    	personneVoyageKey.setVoyageId(request.getVoyageId());
    	
    	// la personneVoyage
    	personneVoyage=new PersonneVoyage ();
    	personneVoyage.setPersonneVoyageKey(personneVoyageKey);
    	
    	personneVoyageRepository.delete(personneVoyage);
    	
    	return personneVoyage;
    }

    
    public boolean isVoyageExiste (Voyage voyageCritere) {
    	return voyageRepository.findAll(VoyageRepository.hasVoyage(voyageCritere)).size ()>0;
    }
    

    public ActiviteVoyage addActiviteToVoyage (ActiviteVoyageAddRequest request) {
    	
    	ActiviteVoyageKey activiteVoyageKey;
    	ActiviteVoyage activiteVoyage;
    	Optional<Activite> activite;
    	Optional<Voyage> voyage;
    	
    	// l'activite
    	activite=activiteRepository.findById(request.getActiviteId());
    	if (!activite.isPresent()) {
    		throw new EntityNotFoundException("l'activit\u00E9 "+request.getActiviteId()+" n'existe pas");
    	}
    	
    	// le voyage
    	voyage=voyageRepository.findById(request.getVoyageId());
    	if (!voyage.isPresent()) {
    		throw new EntityNotFoundException ("le voyage "+request.getVoyageId()+" n'existe pas");
    	}
    	
    	// les dates
    	if (request.getDateDebut().getTime()>request.getDateFin().getTime()) {
    		throw new EntityNotFoundException ("les dates sont invalides");
    	}
    	
    	
    	// la cle
    	activiteVoyageKey=new ActiviteVoyageKey ();    	
    	activiteVoyageKey.setActiviteId(request.getActiviteId());
    	activiteVoyageKey.setVoyageId(request.getVoyageId());
    	
    	// la activiteVoyage
    	activiteVoyage=new ActiviteVoyage ();
    	activiteVoyage.setActiviteVoyageKey(activiteVoyageKey);
    	activiteVoyage.setDateDebut(request.getDateDebut());
    	activiteVoyage.setDateFin(request.getDateFin());
    	
    	return activiteVoyageRepository.save(activiteVoyage);
    }

    public double calculerCoutTotal (Voyage voyage) {
    	double cout=0;
    	
    	for (ActiviteVoyage activite:voyage.getActiviteVoyage()) {
    		
    		cout+=activite.getActivite().getCout();
    		
    	}
    	
    	return cout;
    }
    
    public Date calculerDebutVoyage (Voyage voyage) {
		long d=Long.MAX_VALUE;
		
		for (ActiviteVoyage activiteVoyage:voyage.getActiviteVoyage ()) {
			
			if (activiteVoyage.getDateDebut().getTime()<d) {
				d=activiteVoyage.getDateDebut().getTime();
			}
		}

		if (d==Long.MAX_VALUE) {
			return null;
		}
		
		return new Date (d);
    }
    
    public Date calculerFinVoyage (Voyage voyage) {
		long d=Long.MIN_VALUE;
		
		for (ActiviteVoyage activiteVoyage:voyage.getActiviteVoyage ()) {
			
			if (activiteVoyage.getDateFin().getTime()>d) {
				d=activiteVoyage.getDateFin().getTime();
			}
		}

		if (d==Long.MIN_VALUE) {
			return null;
		}
		
		return new Date (d);
    	
    }
}
