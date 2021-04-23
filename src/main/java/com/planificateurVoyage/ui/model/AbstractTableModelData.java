package com.planificateurVoyage.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.springframework.beans.BeanUtils;

import com.planificateurVoyage.model.IDataModel;


public abstract class AbstractTableModelData<T extends IDataModel> extends AbstractTableModel {


	private String[] columnName;

	protected void setColumnName (String[] columnName) {
		this.columnName=columnName;
	}
	
	private List<T> listData=new ArrayList<> ();
	
	public List<T> getListData (){
		return listData;
	}
	
	public T getData (int index) {
		return listData.get(index);
	}
	

	public void initialiserModel () {
		initialiserColumnName ();
	}
	
	public abstract void initialiserColumnName ();

	
	@Override
	public int getRowCount() {
		return listData.size();
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

//	@Override
//	public Object getValueAt(int rowIndex, int columnIndex) {
//
//			if (columnIndex==0)
//			{
//				return listPersonne.get(rowIndex).getId();
//			}
//			else if (columnIndex==1)
//			{
//				return listPersonne.get(rowIndex).getNom();
//			}
//			else if (columnIndex==2)
//			{
//				return listPersonne.get(rowIndex).getPrenom();
//			}
//
//		return null;
//	}

	@Override
	public String getColumnName(int col) {
	    return columnName[col];
	}
	
	public void addData (T data) {
		listData.add(data);
		
		fireTableRowsInserted(listData.size()-1, listData.size()-1);
	}
	

	public void replaceData (T data) {
		boolean nonTrouve=true;
		int i=0;
		
		while ((i<getListData ().size()) && nonTrouve) {
			if (getListData ().get(i).getId()==data.getId()) {
				BeanUtils.copyProperties(data, getListData ().get(i));
				
				nonTrouve=false;
				
				fireTableRowsUpdated(i, i);
			}
			
			i++;
		}
	}

	

	public void clearData () {
		int tailleList=listData.size();
		
		if (tailleList>0) {
			listData.clear();
			fireTableRowsDeleted(0, tailleList-1);
		}
	}
	
	public void setModel (List<T> listData) {
		clearData();
		
		for (T data:listData) {
			addData(data);
		}
	}
	
}
