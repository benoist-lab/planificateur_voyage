package com.planificateurVoyage.servicerest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.model.request.CategorieCreateRequest;
import com.planificateurVoyage.service.CategorieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping (value="/api/planificateurvoyage")
@RequiredArgsConstructor
@CrossOrigin (origins="*")
public class CategorieController {
	
	private final CategorieService categorieService;
	
	@GetMapping ("/categorie/{id}")
	public ResponseEntity readCategorie (@PathVariable Long id) {
		return ResponseEntity.ok(categorieService.readCategorie(id));
	}

	@GetMapping ("/categories")
	public ResponseEntity readCategories () {
		return ResponseEntity.ok(categorieService.readCategories());
	}

	@PostMapping ("/categorie")
	public ResponseEntity<Categorie> createCategorie (@RequestBody CategorieCreateRequest request){
		return ResponseEntity.ok(categorieService.createCategorie(request));
	}
	
	
	
}
