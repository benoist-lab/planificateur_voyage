package com.planificateurVoyage.servicerest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.request.PaysCreateRequest;
import com.planificateurVoyage.model.request.PaysDeleteRequest;
import com.planificateurVoyage.model.request.PersonneCreateRequest;
import com.planificateurVoyage.service.PaysService;
import com.planificateurVoyage.service.PersonneService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/planificateurvoyage")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaysController {
	
	private final PaysService paysService;
	
	
	@GetMapping ("/pays/{id}")
	public ResponseEntity readPays (@PathVariable Long id) {
		return ResponseEntity.ok(paysService.readPays(id));
	}
	
	@GetMapping ("/pays")
	public ResponseEntity readPays () {
		return ResponseEntity.ok(paysService.readPays());
	}
	
	
	@PostMapping("/pays")
    public ResponseEntity<Pays> createPays (@RequestBody PaysCreateRequest request) {
		
		return ResponseEntity.ok(paysService.createPays(request));
    }
	
	@DeleteMapping ("/pays")
	public ResponseEntity deletePays (@RequestBody PaysDeleteRequest request) {
		
		return ResponseEntity.ok (paysService.supprimerPays(request));
		
	}

}
