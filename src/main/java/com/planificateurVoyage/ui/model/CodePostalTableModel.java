package com.planificateurVoyage.ui.model;

import com.planificateurVoyage.model.CodePostal;


public class CodePostalTableModel extends AbstractTableModelData<CodePostal> {
	
	@Override
	public void initialiserColumnName() {
		String [] nomColonne={"ID", "CODE POSTAL", "VILLE", "PAYS"};
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
				return getListData ().get(rowIndex).getCodePostal();
			}
			else if (columnIndex==2)
			{
				return getListData ().get(rowIndex).getVille();
			}
			else if (columnIndex==3)
			{
				return getListData ().get(rowIndex).getPays();
			}


		return null;
	}
	
}
