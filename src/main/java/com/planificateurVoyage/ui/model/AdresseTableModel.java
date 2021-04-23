package com.planificateurVoyage.ui.model;

import com.planificateurVoyage.model.Adresse;

public class AdresseTableModel extends AbstractTableModelData<Adresse> {

	@Override
	public void initialiserColumnName() {
		String [] nomColonne={"ID", "LIBELLE", "LIGNE 1", "LIGNE2", "LIGNE3", "CODE POSTAL", "VILLE","PAYS"};
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
			else if (columnIndex==2)
			{
				return getListData ().get(rowIndex).getLigne1();
			}
			else if (columnIndex==3)
			{
				return getListData ().get(rowIndex).getLigne2();
			}
			else if (columnIndex==4)
			{
				return getListData ().get(rowIndex).getLigne3();
			}
			else if (columnIndex==5)
			{
				if (getListData ().get(rowIndex).getCodePostal()==null) {
					return "";
				}
				return getListData ().get(rowIndex).getCodePostal().getCodePostal();
			}
			else if (columnIndex==6)
			{
				if (getListData ().get(rowIndex).getCodePostal()==null) {
					return "";
				}
				return getListData ().get(rowIndex).getCodePostal().getVille();
			}
			else if (columnIndex==7)
			{
				if (getListData ().get(rowIndex).getCodePostal()==null) {
					return "";
				}
				if (getListData ().get(rowIndex).getCodePostal().getPays()==null) {
					return "";
				}
				return getListData ().get(rowIndex).getCodePostal().getPays().getPays();
			}

		return null;
	}

}
