package com.planificateurVoyage.ui.tool;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

public class JTableUI extends JTable {

	public JTableUI () {
		super();
	}
	
	public JTableUI (TableModel model) {
		this ();
		setModel(model);
		
		setRowHeight(((Dimension)UIManager.get("Label.dimension")).height);
	}
}
