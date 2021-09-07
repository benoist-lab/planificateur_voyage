package com.planificateurVoyage.ui.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.planificateurVoyage.model.ActiviteVoyage;
import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.Voyage;
import com.planificateurVoyage.service.VoyageService;
import com.planificateurVoyage.ui.MainFrame;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VoyageTableModel extends AbstractTableModelData<Voyage> {
	
	private final VoyageService voyageService;

    private static final Logger logger = LoggerFactory.getLogger(VoyageTableModel.class);
    
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");
    
	@Override
	public void initialiserColumnName() {
		String [] nomColonne={"ID", "DEBUT", "FIN", "LIBELLE", "STATUT", "DESCRIPTION", "nb Personne","nb Activite","cout"};
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
				
				Date d=voyageService.calculerDebutVoyage(getListData ().get(rowIndex));
				if (d==null) {
					return "";
				}
				return formatter.format (d);

			}
			else if (columnIndex==2)
			{
				
				Date d=voyageService.calculerFinVoyage(getListData ().get(rowIndex));
				if (d==null) {
					return "";
				}
				return formatter.format (d);
			}
			else if (columnIndex==3)
			{
				return getListData ().get(rowIndex).getLibelle();
			}
			else if (columnIndex==4)
			{
				return getListData ().get(rowIndex).getStatut().getLibelle();
			}
			else if (columnIndex==5)
			{
				return getListData ().get(rowIndex).getDescription();
			}
			else if (columnIndex==6)
			{
				return getListData ().get(rowIndex).getPersonneVoyage().size();
			}
			else if (columnIndex==7)
			{
				return getListData ().get(rowIndex).getActiviteVoyage ().size();
			}
			else if (columnIndex==8)
			{
				return voyageService.calculerCoutTotal(getListData ().get(rowIndex));
			}

		return null;
	}

}