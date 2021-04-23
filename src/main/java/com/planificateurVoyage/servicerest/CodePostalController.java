package com.planificateurVoyage.servicerest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.request.CodePostalCreateRequest;
import com.planificateurVoyage.service.CodePostalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/planificateurvoyage")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CodePostalController {
	
	private final CodePostalService codePostalService;
	
	
	@GetMapping ("/codepostal/{id}")
	public ResponseEntity readCodePostal (@PathVariable Long id) {
		return ResponseEntity.ok(codePostalService.readCodePostal(id));
	}
	
	@GetMapping("/codepostaux")
	public ResponseEntity readCodePostaux () {
		return ResponseEntity.ok(codePostalService.readCodePostaux());
	}
	
	@GetMapping("/codepostaux/{codePostal}")
	public ResponseEntity readCodePostaux (@PathVariable String codePostal) {
		return ResponseEntity.ok(codePostalService.readCodePostaux(codePostal));
	}

	@PostMapping ("/codepostal")
	public ResponseEntity<CodePostal> createCodePostal (@RequestBody CodePostalCreateRequest request){
		return ResponseEntity.ok(codePostalService.createCodePostal(request));
	}
	
	@GetMapping("/codepostaux/liste")
	public ResponseEntity readListeCodePostaux () {
		return ResponseEntity.ok(codePostalService.getListeCodePostaux());
	}
	
}
