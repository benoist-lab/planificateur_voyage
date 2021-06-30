package com.planificateurVoyage.ui.tool;

import javax.swing.JTextArea;

import com.planificateurVoyage.ui.tool.adpater.FocusBorderAdpater;

public class JTextAreaUI extends JTextArea  {
	
	public JTextAreaUI () {
		super();

		addFocusListener(new FocusBorderAdpater ());
	}
}
