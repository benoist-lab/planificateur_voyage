package com.planificateurVoyage.ui.model;

import java.util.List;

import javax.swing.DefaultComboBoxModel;


public class AbstractComboBoxModelData<T> extends DefaultComboBoxModel {

	public void setModel (List<T> listData) {

		removeAllElements();
		
		for (T data:listData) {
			addElement(data);
		}
	}
}
