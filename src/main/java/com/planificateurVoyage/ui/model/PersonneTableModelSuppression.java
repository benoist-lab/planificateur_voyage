package com.planificateurVoyage.ui.model;

public class PersonneTableModelSuppression extends PersonneTableModel{

	@Override
	public void initialiserColumnName() {
		String [] nomColonne={"ID", "NOM", "PRENOM", " "};
		setColumnName(nomColonne);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (columnIndex==3)
		{
			return "Supprimer";
		}
		else
		{
			return super.getValueAt(rowIndex, columnIndex);
		}
	}
	
}
