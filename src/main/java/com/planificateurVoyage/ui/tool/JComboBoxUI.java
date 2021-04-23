package com.planificateurVoyage.ui.tool;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import com.planificateurVoyage.model.IDataModel;
import com.planificateurVoyage.ui.tool.adpater.FocusBorderAdpater;

public class JComboBoxUI extends JComboBox{
	
	public JComboBoxUI () {
		super ();
		setSize (UIManager.getDimension(("ComboBox.dimension")));
		
		addFocusListener(new FocusBorderAdpater ());
	}
	
	public JComboBoxUI (ComboBoxModel model) {
		this ();
		
		setModel (model);

	}
	
	public void setSelectedItem (IDataModel data) {
		getModel ().setSelectedItem(data);
	}
	
}
