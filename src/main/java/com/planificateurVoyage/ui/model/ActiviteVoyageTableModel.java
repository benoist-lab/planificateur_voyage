package com.planificateurVoyage.ui.model;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.ActiviteVoyage;
import com.planificateurVoyage.model.Voyage;
import com.planificateurVoyage.ui.MainFrame;

public class ActiviteVoyageTableModel extends AbstractTableModel {
	
	private String[] columnName;

	protected void setColumnName (String[] columnName) {
		this.columnName=columnName;
	}
	
	private Voyage data=new Voyage ();
	
	/*
	public void addData (T data) {
		listData.add(data);
		
		fireTableRowsInserted(listData.size()-1, listData.size()-1);
	}
	 */
	public void setData (Voyage data) {
		clearData ();
		this.data=data;
		
		fireTableRowsInserted(0, data.getActiviteVoyage().size()-1);
	}
	
	public Voyage getData () {
		return data;
	}
	
	public List<ActiviteVoyage> getListData () {
		return new LinkedList (data.getActiviteVoyage());
	}
	
	public void addActiviteData (ActiviteVoyage activite) {
		data.getActiviteVoyage().add(activite);
		
		this.fireTableRowsInserted(data.getActiviteVoyage().size(),data.getActiviteVoyage().size());
	}
	
	public ActiviteVoyage getActiviteData (int index) {
		return (ActiviteVoyage) data.getActiviteVoyage().toArray()[index];
	}
	
	public void initialiserColumnName() {
//		String [] nomColonne={"DEBUT", "FIN", "ID", "CATEGORIE", "LIBELLE", "ADRESSE", "COUT U", "COUT", "DESCRIPTION"};
		String [] nomColonne={"DEBUT", "FIN", "ID", "CATEGORIE", "LIBELLE", "COUT U", "COUT", "DESCRIPTION"};
			setColumnName(nomColonne);
	}
	
	public static final SimpleDateFormat formatDate=new SimpleDateFormat ("HH:mm dd/MM/yyyy");

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (columnIndex==0)
		{
			return formatDate.format(getActiviteVoyage (rowIndex).getDateDebut());
		}
		else if (columnIndex==1)
		{
			return formatDate.format(getActiviteVoyage (rowIndex).getDateFin());
		}
		else if (columnIndex==2)
		{
			return getActiviteVoyage (rowIndex).getActiviteVoyageKey().getActiviteId();
		}
		else if (columnIndex==3)
		{
			return getActiviteVoyage (rowIndex).getActivite().getCategorie().getLibelle();
		}
		else if (columnIndex==4)
		{
			return getActiviteVoyage (rowIndex).getActivite().getLibelle();
		}
//		else if (columnIndex==5)
//		{
//			return getActiviteVoyage (rowIndex).getActivite().getAdresse().getAdresseLigne();
//		}
//		else if (columnIndex==6)
		else if (columnIndex==5)
		{
			return getActiviteVoyage (rowIndex).getActivite().getCout();
		}
//		else if (columnIndex==7)
		else if (columnIndex==6)
		{
			if (getActiviteVoyage (rowIndex).getActivite().isForfaitaire()) {
				return getActiviteVoyage (rowIndex).getActivite().getCout();
			}
			return getActiviteVoyage (rowIndex).getActivite().getCout()*data.getPersonneVoyage().size();
		}
//		else if (columnIndex==8)
		else if (columnIndex==7)
		{
			return getActiviteVoyage (rowIndex).getActivite().getDescription();
		}

		return null;
	}

	@Override
	public int getRowCount() {
		return data.getActiviteVoyage().size();
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}
	

	@Override
	public String getColumnName(int col) {
	    return columnName[col];
	}
	
	
	public void initialiserModel () {
		initialiserColumnName ();
	}
	
	public ActiviteVoyage getActiviteVoyage (int index) {
		int i=0;
		ActiviteVoyage resultat=null;
		Iterator<ActiviteVoyage> iteration;
		
		if ((index+1)>data.getActiviteVoyage().size()) {
			throw new RuntimeException ("l'index recherché déborde de la liste des activitées");
		}
		
		iteration=data.getActiviteVoyage().iterator();
				
		while (resultat==null) {
			
			if (index==i) {
				resultat=iteration.next();
			}else{
				iteration.next();
			}
			
			i++;
		}
		
		return resultat;
	}
	

	public void removeData (ActiviteVoyage activite) {
		int index=getIndexActivite (activite);
		
		data.getActiviteVoyage().remove(activite);
		
		fireTableRowsDeleted(index, index);
		

	}
	
	public int getIndexActivite (ActiviteVoyage activite) {
		int index=-1;
		int i=0;
		Object[] tableauActivite=data.getActiviteVoyage().toArray();
		
		while ((index==-1) && (i<tableauActivite.length)) {
			
			if (activite.equals(tableauActivite[i])) {
				index=i;
			}
			
			i++;
		}
		
		return index;
	}

	/*
	public void replaceData (ActiviteVoyage activite) {

	}
	*/

	public boolean containtActiviteData (ActiviteVoyage activite) {
		boolean nonTrouve=true;

		int i=0;
		ActiviteVoyage tmp=null;
		Iterator<ActiviteVoyage> iteration;
			
		iteration=data.getActiviteVoyage().iterator();
					
		while ((nonTrouve==true) && (iteration.hasNext())) {
			tmp=iteration.next();
			
			if (tmp.equals(activite)){
				nonTrouve=false;
			}
				
			i++;
		}
		
		return !nonTrouve;
	}


	public void clearData () {
		int tailleList=data.getActiviteVoyage().size();
		
		if (tailleList>0) {
			data=new Voyage ();
			fireTableRowsDeleted(0, tailleList-1);
		}
	}
}
