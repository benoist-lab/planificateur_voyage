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

import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.request.PaysCreateRequest;
import com.planificateurVoyage.model.request.PaysDeleteRequest;
import com.planificateurVoyage.model.request.PersonneCreateRequest;
import com.planificateurVoyage.repository.PaysRepository;
import com.planificateurVoyage.repository.PersonneRepository;
import com.planificateurVoyage.tools.ToolRegex;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaysService {
	
	private static final Logger logger = LoggerFactory.getLogger(PaysService.class);
	
	private final PaysRepository paysRepository;
	
	
	public Pays readPays (Long id) {
		Optional <Pays> pays=paysRepository.findById(id);
		
		if (pays.isPresent())
		{
			return pays.get();
		}
		
        throw new EntityNotFoundException("ne trouve pas le pays ID="+id);
	}
	
	
	public List<Pays> readPays () {
		return paysRepository.findAll();
	}
	

	//@Transactional
	public Pays createPays (PaysCreateRequest pays)  {
		
		// Controlle des information
		Pays paysCritere=new Pays ();
		paysCritere.setPays(pays.getPays());
		
		/*
		if (isPaysExiste (paysCritere))
		{
			throw new EntityExistsException ("le pays existe");
		}
		 */
		if (pays.getId()==null) {
			if (isPaysExiste (paysCritere))
			{
				throw new EntityExistsException ("le pays existe");
			}
		} else {
			if (readPays (pays.getId())==null) {
				throw new EntityNotFoundException ("le pays n'existe pas");
			}
		}
		
		/*
		if (request.getId()==null) {
			if (isAdresseExiste (adresseCritere)) {
				throw new EntityExistsException ("l'adresse existe");
			}
		}
		else {
			if (readAdresse (request.getId())==null) {
				throw new EntityExistsException ("l'adresse n'existe pas");
			}
		}		 */
		
		return insererPays (pays);
	}
	
	@Transactional
	private Pays insererPays (PaysCreateRequest pays) {
        Pays paysToCreate = new Pays();

        BeanUtils.copyProperties(pays, paysToCreate);
        
        // mise en majuscule du pays
        paysToCreate.setPays(paysToCreate.getPays().toUpperCase());
        
        validerPays (paysToCreate);
        

        
        return paysRepository.save(paysToCreate);
	}
	
	
	/*
	 * @throws PaysInvalidException
	 */
	public void validerPays (Pays pays) {
		ToolRegex.validePays(pays.getPays(), "pays");
	}

	
	public boolean isPaysExiste (Pays paysCritere) {
		Pays pays = new Pays();
		
		BeanUtils.copyProperties(paysCritere, pays);
        
        // mise en majuscule du pays
		//if (pays.getPays()!=null) {		
			pays.setPays(pays.getPays().toUpperCase());
		//}
        
		return paysRepository.findAll(PaysRepository.hasPays (pays)).size()>0;
	}
	
	public Pays supprimerPays (PaysDeleteRequest request) {
		Pays pays=null;
		
		//BeanUtils.copyProperties(request, pays);
		
		try {
			pays=readPays (request.getId());
		}catch (Exception e) {
			throw new EntityNotFoundException ("Le pays ("+request.getId()+") ?? supprimer n'existe pas");
		}
		
		logger.info("delete du pays: "+pays.getId());
		
		paysRepository.delete(pays);
		
		return pays;
	}

}
