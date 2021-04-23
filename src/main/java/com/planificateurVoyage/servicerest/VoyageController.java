package com.planificateurVoyage.servicerest;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificateurVoyage.model.PersonneVoyage;
import com.planificateurVoyage.model.Voyage;
import com.planificateurVoyage.model.request.PersonneVoyageAddRequest;
import com.planificateurVoyage.model.request.VoyageCreateRequest;
import com.planificateurVoyage.service.VoyageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping (value = "/api/planificateurvoyage")
@RequiredArgsConstructor
@CrossOrigin (origins="*")
public class VoyageController {
	
	private final VoyageService voyageService;
	
	@GetMapping ("/voyages")
	public ResponseEntity readVoyages () {
		return ResponseEntity.ok(voyageService.readVoyages());
	}
	
	
	
	@PostMapping ("/voyage")
	public ResponseEntity<Voyage> createVoyage (@RequestBody VoyageCreateRequest request) {
		return ResponseEntity.ok(voyageService.createVoyage(request));
	}
	
	@PostMapping ("/voyage/personne")
	public ResponseEntity<PersonneVoyage> addPersonneToVoyage (@RequestBody PersonneVoyageAddRequest request){
		return ResponseEntity.ok(voyageService.addPersonneToVoyage(request));
	}
	
	@GetMapping ("/voyage/personne/{id}")
	public ResponseEntity readPersonneFromVoyage (@PathVariable Long id) {
		return ResponseEntity.ok(voyageService.readVoyagePersonnes (id));
	}

	@GetMapping ("/voyage/personne/info/{id}")
	public ResponseEntity readPersonneInformationFromVoyage (@PathVariable Long id) {
		return ResponseEntity.ok(voyageService.readVoyagePersonnesInformation (id));
	}

	
	@GetMapping ("/voyage/voyage/personne/{id}")
	public ResponseEntity readVoyageFromPersonne (@PathVariable Long id) {
		return ResponseEntity.ok(voyageService.readVoyageParPersonne (id));
	}
 
	

}
