package com.planificateurVoyage.ui.model;

import com.planificateurVoyage.model.Activite;

public class ActiviteTableModel extends AbstractTableModelData<Activite> {

	@Override
	public void initialiserColumnName() {
		String [] nomColonne={"ID", "CATEGORIE", "LIBELLE", "ADRESSE", "COUT", "FORFAIT", "DESCRIPTION"};
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
				return getListData ().get(rowIndex).getCategorie().getLibelle();
			}
			else if (columnIndex==2)
			{
				return getListData ().get(rowIndex).getLibelle();
			}
			else if (columnIndex==3)
			{
				return getListData ().get(rowIndex).getAdresse().getAdresseLigne();
			}
			else if (columnIndex==4)
			{
				return getListData ().get(rowIndex).getCout();
			}
			else if (columnIndex==5)
			{
				return getListData ().get(rowIndex).isForfaitaire()?"oui":"non";
			}
			else if (columnIndex==6)
			{
				return getListData ().get(rowIndex).getDescription();
			}

		return null;
	}
	

}
