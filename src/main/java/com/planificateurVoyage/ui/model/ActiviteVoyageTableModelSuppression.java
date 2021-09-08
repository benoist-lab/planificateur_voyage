package com.planificateurVoyage.ui.model;

public class ActiviteVoyageTableModelSuppression extends ActiviteVoyageTableModel {


	@Override
	public void initialiserColumnName() {
//		String [] nomColonne={"DEBUT", "FIN", "ID", "CATEGORIE", "LIBELLE", "ADRESSE", "COUT U", "COUT", "DESCRIPTION", " "};
		String [] nomColonne={"DEBUT", "FIN", "ID", "CATEGORIE", "LIBELLE", "COUT U", "COUT", "DESCRIPTION", " "};
			setColumnName(nomColonne);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
//		if (columnIndex==9)
		if (columnIndex==8)
				{
			return "Supprimer";
		}
		else
		{
			return super.getValueAt(rowIndex, columnIndex);
		}
	}
	
	
}
