package com.planificateurVoyage.ui.tool;

import javax.swing.JCheckBox;
import javax.swing.UIManager;

import com.planificateurVoyage.ui.tool.adpater.FocusBorderAdpater;

public class JCheckBoxUI extends JCheckBox {

	public JCheckBoxUI () {
		super();
		setSize (UIManager.getDimension(("CheckBox.dimension")));
		
		addFocusListener(new FocusBorderAdpater ());

	}
	
	public JCheckBoxUI (String libelle) {
		this();
		setText (libelle);
	}
	
}
