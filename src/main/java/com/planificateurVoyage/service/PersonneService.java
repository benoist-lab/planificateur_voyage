package com.planificateurVoyage.service;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.request.PersonneCreateRequest;
import com.planificateurVoyage.repository.PersonneRepository;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.tools.exception.NomInvalidException;
import com.planificateurVoyage.ui.MainFrame;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//@Transactional
public class PersonneService {

	
	private static final Logger logger = LoggerFactory.getLogger(PersonneService.class);
	
	private final PersonneRepository personneRepository;

	public Personne readPersonne (Long id) {
		Optional <Personne> personne=personneRepository.findById(id);
		
		if (personne.isPresent())
		{
			return personne.get();
		}
		
        throw new EntityNotFoundException("ne trouve pas la personne ID="+id);
	}
	
	public List<Personne> readPersonnes () {
		return personneRepository.findAll();
	}
	
	//@Transactional
	public Personne createPersonne (PersonneCreateRequest personne)  {
		
		// Controlle des information
		if (personne.getId()==null) {
			Personne personneCritere=new Personne ();
			personneCritere.setNom(personne.getNom());
			personneCritere.setPrenom(personne.getPrenom());
			
			if (isPersonneExiste (personneCritere))
			{
				throw new EntityExistsException ("la personne existe");
			}
		}
		else {
			if (readPersonne (personne.getId())==null) {
				throw new EntityExistsException ("la personne n'existe pas");
			}
		}
		
		return insererPersonne (personne);
	}
	
	/*
	 * @throws AdresseInvalidException, CodePostalInvalidException, VilleInvalidException, PaysInvalidException
	 */
	public void validerPersonne (Personne personne) {
        ToolRegex.valideNom(personne.getNom(), "nom");
        ToolRegex.validePrenom(personne.getPrenom(), "pr\u00E9nom");
	}
	
	@Transactional
	private Personne insererPersonne (PersonneCreateRequest personne) {
        Personne personneToCreate = new Personne();

        BeanUtils.copyProperties(personne, personneToCreate);
        
        validerPersonne (personneToCreate) ;
        
        return personneRepository.save(personneToCreate);
	}
	
	public boolean isPersonneExiste (Personne personneCritere) {
		return personneRepository.findAll(PersonneRepository.hasPersonne (personneCritere)).size()>0;
	}
	
	public List<Personne> rechercherPersonne (Personne personneCritere) {
		return personneRepository.findAll(PersonneRepository.hasPersonneLike (personneCritere));
	}
	
}
