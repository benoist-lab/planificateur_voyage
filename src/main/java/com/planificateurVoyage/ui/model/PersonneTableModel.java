package com.planificateurVoyage.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.ui.MainFrame;


public class PersonneTableModel extends AbstractTableModelData<Personne> {

	@Override
	public void initialiserColumnName() {
		String [] nomColonne={"ID", "NOM", "PRENOM"};
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
				return getListData ().get(rowIndex).getNom();
			}
			else if (columnIndex==2)
			{
				return getListData ().get(rowIndex).getPrenom();
			}

		return null;
	}
	

}
