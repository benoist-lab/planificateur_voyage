package com.planificateurVoyage.servicerest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificateurVoyage.model.StatutVoyage;
import com.planificateurVoyage.model.request.StatutVoyageCreateRequest;
import com.planificateurVoyage.service.StatutVoyageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping (value="/api/planificateurvoyage")
@RequiredArgsConstructor
@CrossOrigin (origins="*")
public class StatutVoyageController {

	private final StatutVoyageService statutVoyageService;
	
	@GetMapping ("/statutvoyage/{id}")
	public ResponseEntity readStatutVoyage (@PathVariable Long id) {
		return ResponseEntity.ok(statutVoyageService.readStatutVoyage(id));
	}
	
	@GetMapping("/statutvoyages")
	public ResponseEntity readStatutVoyages () {
		return ResponseEntity.ok(statutVoyageService.readStatutVoyages());
	}

	@PostMapping ("/statutvoyage")
	public ResponseEntity<StatutVoyage> createStatutVoyage (@RequestBody StatutVoyageCreateRequest request){
		return ResponseEntity.ok(statutVoyageService.createStatutVoyage(request));
	}
	
}
