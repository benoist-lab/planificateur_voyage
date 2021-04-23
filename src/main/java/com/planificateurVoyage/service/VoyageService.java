package com.planificateurVoyage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.PersonneVoyage;
import com.planificateurVoyage.model.PersonneVoyageKey;
import com.planificateurVoyage.model.Voyage;
import com.planificateurVoyage.model.request.PersonneVoyageAddRequest;
import com.planificateurVoyage.model.request.VoyageCreateRequest;
import com.planificateurVoyage.repository.PersonneRepository;
import com.planificateurVoyage.repository.PersonneVoyageRepository;
import com.planificateurVoyage.repository.VoyageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoyageService {

	private final VoyageRepository voyageRepository;
	
	private final PersonneRepository personneRepository;
	
	private final PersonneVoyageRepository personneVoyageRepository;

    private static final Logger logger = LoggerFactory.getLogger(VoyageService.class);
	
	
    
    
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
    public Voyage createVoyage (VoyageCreateRequest voyage) {
    	
    	Voyage voyageToCreate = new Voyage ();
    	BeanUtils.copyProperties(voyage, voyageToCreate);
    	
    	return voyageRepository.save(voyageToCreate);
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
    
}
