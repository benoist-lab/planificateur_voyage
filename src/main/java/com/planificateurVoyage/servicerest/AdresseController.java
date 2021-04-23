package com.planificateurVoyage.servicerest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.request.AdresseCreateRequest;
import com.planificateurVoyage.model.request.AdresseRechercheRequest;
import com.planificateurVoyage.service.AdresseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/planificateurvoyage")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class AdresseController {
	
	private final AdresseService adresseService;
	
	@GetMapping ("/adresse/{id}")
	public ResponseEntity readAdresse(@PathVariable Long id) {
		return ResponseEntity.ok(adresseService.readAdresse(id));
	}
	
	@GetMapping ("/adresses")
	public ResponseEntity readAdresses (){
		return ResponseEntity.ok(adresseService.readAdresses());
	}
	
	@PostMapping ("/adresse")
	public ResponseEntity<Adresse> createAdresse (@RequestBody AdresseCreateRequest request) {
		return ResponseEntity.ok(adresseService.createAdresse(request));
	}
	
	@GetMapping("/adresse/find")
	public ResponseEntity findAdresse (@RequestBody AdresseRechercheRequest request) {
		Adresse adresse=new Adresse ();
		BeanUtils.copyProperties(request, adresse);
		
		return ResponseEntity.ok(adresseService.rechercherAdresse(adresse));
	}

}
