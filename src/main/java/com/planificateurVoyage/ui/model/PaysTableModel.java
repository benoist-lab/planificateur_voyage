package com.planificateurVoyage.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.planificateurVoyage.model.Pays;


public class PaysTableModel extends AbstractTableModelData<Pays> {


	@Override
	public void initialiserColumnName() {
		String [] nomColonne={"ID", "PAYS"};
		setColumnName(nomColonne);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

			if (columnIndex==0)
			{
				return getListData ().get(rowIndex).getId();
			}
			else if (columnIndex==1)
			{
				return getListData ().get(rowIndex).getPays();
			}


		return null;
	}



}
