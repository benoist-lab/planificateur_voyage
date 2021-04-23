package com.planificateurVoyage.servicerest;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.request.ActiviteCreateRequest;
import com.planificateurVoyage.service.ActiviteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/planificateurvoyage")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ActiviteController {
	
	
	private final ActiviteService activiteService;
	
	@GetMapping ("/activite/{id}")
	public ResponseEntity readActivite(@PathVariable Long id) {
		return ResponseEntity.ok(activiteService.readActivite(id));
	}
	
	@GetMapping ("/activites")
	public ResponseEntity readActivites (){
		return ResponseEntity.ok(activiteService.readActivites());
	}
	
	@PostMapping ("/activite")
	public ResponseEntity<Activite> createActivite (@RequestBody ActiviteCreateRequest request) {
		return ResponseEntity.ok(activiteService.createActivite(request));
	}
	
//	@GetMapping("/activite/find")
//	public ResponseEntity findActivite (@RequestBody ActiviteRechercheRequest request) {
//		Activite activite=new Activite ();
//		BeanUtils.copyProperties(request, activite);
//		
//		return ResponseEntity.ok(activiteService.rechercherActivite(adresse));
//	}
	

}
