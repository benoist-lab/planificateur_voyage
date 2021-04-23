package com.planificateurVoyage.ui.tool;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import com.planificateurVoyage.ui.tool.adpater.FocusBorderAdpater;

public class JTextFieldUI extends JTextField {

	public JTextFieldUI () {
		super ();
		setColumns(10);
		setSize (UIManager.getDimension("TextField.dimension"));
		
		addFocusListener(new FocusBorderAdpater ());
	}
	

	public void setDocumentFilter (DocumentFilter filter) {
		((AbstractDocument) getDocument()).setDocumentFilter(filter);
	}
}
