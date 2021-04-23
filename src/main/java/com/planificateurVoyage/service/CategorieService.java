package com.planificateurVoyage.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.model.request.CategorieCreateRequest;
import com.planificateurVoyage.repository.CategorieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategorieService {

	private final CategorieRepository categorieRepository;
	
	public Categorie readCategorie (Long id) {
		Optional <Categorie> categorie;
		categorie= categorieRepository.findById(id);
		
		if (categorie.isPresent()) {
			return categorie.get();
		}
		
		throw new EntityNotFoundException ("ne trouve pas la categorie ID="+id);
	}
	
	public List<Categorie> readCategories () {
		return categorieRepository.findAll();
	}
	
	
	public Categorie createCategorie (CategorieCreateRequest categorie) {
		
		//Controlle des informations
		Categorie categorieCritere=new Categorie ();
		categorieCritere.setLibelle(categorie.getLibelle());
		if (isCategorieExiste (categorieCritere)) {
			throw new EntityExistsException ("la cat\u00E9gorie existe");
		}
		
		return insererCategorie (categorie);
	}
	
	
	
	@Transactional
	private  Categorie insererCategorie (CategorieCreateRequest categorie) {
		Categorie categorieToCreate;
		
		categorieToCreate=new Categorie ();		
		BeanUtils.copyProperties(categorie, categorieToCreate);
		
		return categorieRepository.save(categorieToCreate);
	}
	
	public boolean isCategorieExiste (Categorie categorieCritere) {
		Categorie categorie;
		
		categorie=new Categorie ();
		BeanUtils.copyProperties(categorieCritere, categorie);
		
		return categorieRepository.findAll(CategorieRepository.hasCategorie (categorie)).size ()>0;
	}
	
	
}
