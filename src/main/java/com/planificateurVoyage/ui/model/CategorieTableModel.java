package com.planificateurVoyage.ui.model;

import com.planificateurVoyage.model.Categorie;

public class CategorieTableModel extends AbstractTableModelData<Categorie> {


	@Override
	public void initialiserColumnName() {
		String [] nomColonne={"ID", "CATEGORIE"};
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
				return getListData ().get(rowIndex).getLibelle();
			}


		return null;
	}

}