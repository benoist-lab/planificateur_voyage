package com.planificateurVoyage.ui.model;

import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.service.CodePostalService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CodePostalComboBoxModel extends AbstractComboBoxModelData<String> {

	private final CodePostalService codePostalService;
	
	public void initialiserModel () {
		setModel(codePostalService.getListeCodePostaux());
	}
	
}

