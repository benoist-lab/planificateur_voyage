package com.planificateurVoyage.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.request.AdresseCreateRequest;
import com.planificateurVoyage.repository.AdresseRepository;
import com.planificateurVoyage.repository.PersonneRepository;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.tools.exception.AdresseInvalidException;
import com.planificateurVoyage.ui.MainFrame;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdresseService {
	

    private static final Logger logger = LoggerFactory.getLogger(AdresseService.class);
	
	private final AdresseRepository adresseRepository;
	
	private final CodePostalService codePostalService;
	
	public Adresse readAdresse (Long id) {
		Optional <Adresse> adresse=adresseRepository.findById(id);
		
		if (adresse.isPresent()) {
			return adresse.get();
		}
		
		throw new EntityNotFoundException ("ne trouve pas l'adresse ID="+id);
	}
	
	public List<Adresse> readAdresses (){
		return adresseRepository.findAll();
	}
	
	public Adresse createAdresse (AdresseCreateRequest request) {
		
		//Controle des information
		Adresse adresseCritere=new Adresse ();
		BeanUtils.copyProperties(request, adresseCritere);
		
		try {
			codePostalService.readCodePostal(request.getCodePostal().getId());
		}catch (EntityNotFoundException enfe) {
			throw new EntityExistsException ("le code postal de l'adresse n'existe pas");
		}
		
		if (request.getId()==null) {
			if (isAdresseExiste (adresseCritere)) {
				throw new EntityExistsException ("l'adresse existe");
			}
		}
		else {
			if (readAdresse (request.getId())==null) {
				throw new EntityExistsException ("l'adresse n'existe pas");
			}
		}
		
		return insererAdresse (request);
	}
	
	/*
	 * @throws AdresseInvalidException, CodePostalInvalidException, VilleInvalidException, PaysInvalidException
	 */
	public void validerAdresse (Adresse adresse) {
		
		if (!((!adresse.getLibelle ().isEmpty())
		&& (
				(!adresse.getLigne1().isEmpty())
				|| (!adresse.getLigne2().isEmpty())
				|| (!adresse.getLigne3().isEmpty())
			))) {
			throw new AdresseInvalidException ("l'adresse est imcomplete");
		}

	       ToolRegex.valideLigneAdresseRNVP(adresse.getLibelle(), "libelle");
	       ToolRegex.valideLigneAdresseRNVP(adresse.getLigne1(), "ligne 1");
	       ToolRegex.valideLigneAdresseRNVP(adresse.getLigne2(), "ligne 2");
	       ToolRegex.valideLigneAdresseRNVP(adresse.getLigne3(), "ligne 3");
	       
	       codePostalService.validerCodePostal(adresse.getCodePostal());
	        
	}
	
	@Transactional
	private Adresse insererAdresse (AdresseCreateRequest request) {
		Adresse adresseToCreate=new Adresse ();

		BeanUtils.copyProperties(request, adresseToCreate);
		
		validerAdresse (adresseToCreate);
		
		return adresseRepository.save(adresseToCreate);
	}
	
	public boolean isAdresseExiste (Adresse adresseCritere) {
		return adresseRepository.findAll(AdresseRepository.hasAdresse (adresseCritere)).size()>0;
	}
	
	public List<Adresse> rechercherAdresse (Adresse adresseCritere) {
		return adresseRepository.findAll(AdresseRepository.hasAdresseLike (adresseCritere));
	}

}
