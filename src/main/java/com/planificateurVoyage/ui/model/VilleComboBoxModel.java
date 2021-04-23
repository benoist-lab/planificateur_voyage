package com.planificateurVoyage.ui.model;

import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.service.CodePostalService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VilleComboBoxModel extends AbstractComboBoxModelData<CodePostal> {

	private final CodePostalService codePostalService;
	
	private String dernierCodePostal=null;
	
	public void initialiserModel () {
		
	}
	
	public void initialiserModel (String codePostal) {
		dernierCodePostal=codePostal;
		if (codePostal!=null) {
			setModel(codePostalService.readCodePostaux(codePostal));
		}
	}
	
	public void reinitialiserModel () {
		initialiserModel (dernierCodePostal);
	}
}


