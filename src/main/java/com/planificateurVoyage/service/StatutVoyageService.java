package com.planificateurVoyage.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.planificateurVoyage.model.StatutVoyage;
import com.planificateurVoyage.model.request.StatutVoyageCreateRequest;
import com.planificateurVoyage.repository.StatutVoyageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatutVoyageService {
	
	private final StatutVoyageRepository statutVoyageRepository;
	
	public StatutVoyage readStatutVoyage (Long id) {
		Optional<StatutVoyage> statutVoyage=statutVoyageRepository.findById(id);
		
		if (statutVoyage.isPresent()) {
			return statutVoyage.get();
		}
		
		throw new EntityNotFoundException ("le trouve pas le statut voyage ID="+id);
	}
	
	public List<StatutVoyage> readStatutVoyages (){
		return statutVoyageRepository.findAll();
	}
	
	public StatutVoyage createStatutVoyage (StatutVoyageCreateRequest request) {
		
		//Control des informations
		StatutVoyage statutVoyageCritere=new StatutVoyage ();
		BeanUtils.copyProperties(request, statutVoyageCritere);
		
	
		if (isStatutVoyageExiste (statutVoyageCritere)) {
			throw new EntityExistsException ("le statut voyage existe");
		}
		
		return insererStatutVoyage (request);
	}

	@Transactional
	public StatutVoyage insererStatutVoyage (StatutVoyageCreateRequest request) {
		StatutVoyage statutVoyageToCreate=new StatutVoyage ();
		
		BeanUtils.copyProperties(request, statutVoyageToCreate);
		return statutVoyageRepository.save(statutVoyageToCreate);
	}
	
	public boolean isStatutVoyageExiste (StatutVoyage statutVoyageCritere) {
		return statutVoyageRepository.findAll(StatutVoyageRepository.hasStatutVoyage (statutVoyageCritere)).size ()>0;
	}
	

}
