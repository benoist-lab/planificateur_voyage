package com.planificateurVoyage.ui.tool;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public abstract class AbstractPanelTitle extends JPanelUI {
	
	private String titre;
	public String getTitre () {
		return titre;
	}
	public void setTitre (String titre) {
		this.titre=titre;
		
		getLabelTitre ().setText (titre);
	}
	
	private JLabelUI lTitle;
	protected JLabel getLabelTitre () {
		if (lTitle==null) {
			lTitle=new JLabelUI (titre);

			lTitle.setFont((Font) UIManager.get("Label.font.titre"));
		}
		return lTitle;
	}
	
	protected abstract JPanel getPanneauCentral ();
	
	protected void initUI (String titre) {
		
		setTitre (titre);
		
		setLayout (new BorderLayout ());
		
		JPanelUI panneauTitre=new JPanelUI ();

		panneauTitre.add (getLabelTitre ());
		
		
		add(panneauTitre,BorderLayout.NORTH);

		add(getPanneauCentral (),BorderLayout.CENTER);
	}

}
