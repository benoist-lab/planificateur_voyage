package com.planificateurVoyage.ui.metier.voyage;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.planificateurVoyage.ui.tool.AbstractPanelTitle;

public class VoyageConsultationPanel extends AbstractPanelTitle {
	
	private JPanel panneauCentral;
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			panneauCentral=new JPanel ();
			panneauCentral.setBackground(java.awt.Color.WHITE);
		}
		return panneauCentral;
	}
	
	protected void init () {
		
		
		initUI ("Consultation d'un voyage");
		
	}
	

	

}