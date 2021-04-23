package com.planificateurVoyage.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.request.CodePostalCreateRequest;
import com.planificateurVoyage.repository.CodePostalRepository;
import com.planificateurVoyage.repository.PaysRepository;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.tools.exception.CodePostalInvalidException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodePostalService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private final CodePostalRepository codePostalRepository;
	private final PaysService paysService;
	
	
	public CodePostal readCodePostal (Long id) {
		Optional<CodePostal> codePostal =codePostalRepository.findById(id);
		
		if(codePostal.isPresent()) {
			return codePostal.get();
		}
		
		throw new EntityNotFoundException ("ne trouve pas le code postal ID="+id);
	}
	
	public List<CodePostal> readCodePostaux () {
		return codePostalRepository.findAll();
	}
	
	public List<CodePostal> readCodePostaux (String codePostal) {
		return codePostalRepository.findAll(CodePostalRepository.byCodePostal(codePostal));
	}
	
	
	public CodePostal createCodePostal (CodePostalCreateRequest request) {
		
		//Control des informations
		CodePostal codePostalCritere=new CodePostal ();
		BeanUtils.copyProperties(request, codePostalCritere);
		
		try {
			paysService.readPays(request.getPays().getId());
		}catch (EntityNotFoundException enfe) {
			throw new EntityExistsException ("le pays du code postal n'existe pas");
		}
		
		if (isCodePostalExiste (codePostalCritere)) {
			throw new EntityExistsException ("le code postal existe");
		}
		
		return insererCodePostal (request);
	}
	
	@Transactional
	private CodePostal insererCodePostal (CodePostalCreateRequest request) {
		CodePostal codePostalToCreate=new CodePostal ();
		
		BeanUtils.copyProperties(request, codePostalToCreate);
		
		validerCodePostal (codePostalToCreate);
		
		return codePostalRepository.save(codePostalToCreate);
	}
	
	/*
	 * @throws CodePostalInvalidException, VilleInvalidException, PaysInvalidException
	 */
	public void validerCodePostal (CodePostal codePostal) {
		ToolRegex.valideCodePostal(codePostal.getCodePostal(), "code postal");
		ToolRegex.valideVille(codePostal.getVille(), "ville");
		ToolRegex.validePays(codePostal.getPays().getPays(), "pays");
	}
	
	
	public boolean isCodePostalExiste (CodePostal codePostalCritere) {
		return codePostalRepository.findAll(CodePostalRepository.hasCodePostal(codePostalCritere)).size()>0;
	}
	
	
	public List<String> getListeCodePostaux (){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		Root<CodePostal> root = criteriaQuery.from(CodePostal.class);
		
		criteriaQuery.select(root.get("codePostal")).distinct(true).orderBy(criteriaBuilder.asc(root.get("codePostal")));
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
}
