package com.planificateurVoyage.servicerest;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.request.PersonneCreateRequest;
import com.planificateurVoyage.model.request.PersonneRechercheRequest;
import com.planificateurVoyage.service.PersonneService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/planificateurvoyage")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PersonneController {
	
	
	private final PersonneService personneService;
	
	@GetMapping ("/personne/{id}")
	public ResponseEntity readPersonne (@PathVariable Long id) {
		return ResponseEntity.ok(personneService.readPersonne(id));
	}
	
	@GetMapping ("/personnes")
	public ResponseEntity readPersonne () {
		return ResponseEntity.ok(personneService.readPersonnes());
	}
	
	
	@PostMapping("/personne")
    public ResponseEntity<Personne> createPersonne (@RequestBody PersonneCreateRequest request) {
		
		return ResponseEntity.ok(personneService.createPersonne(request));
    }
	
	@GetMapping ("/personne/find")
	public ResponseEntity<List<Personne>> findPersonne (@RequestBody PersonneRechercheRequest request){
		Personne personne=new Personne ();
		
		personne.setNom(request.getNom ());
		personne.setPrenom(request.getPrenom ());
		
		return ResponseEntity.ok(personneService.rechercherPersonne(personne));
	}
	
	

}

