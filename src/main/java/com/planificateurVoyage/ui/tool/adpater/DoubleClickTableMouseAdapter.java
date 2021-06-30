package com.planificateurVoyage.ui.tool.adpater;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public abstract class DoubleClickTableMouseAdapter extends MouseAdapter{
	
	private int rowClick;
	public int getRowClick () {
		return rowClick;
	}
	
	private int columnClick;
	public int getColumnClick () {
		return columnClick;
	}

    public void mousePressed(MouseEvent mouseEvent) {
        JTable table =(JTable) mouseEvent.getSource();

        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
        	rowClick=table.convertRowIndexToModel(table.rowAtPoint(mouseEvent.getPoint()));
        	columnClick=table.convertColumnIndexToModel(table.columnAtPoint(mouseEvent.getPoint()));
        	onDoubleClick ();
        }
    }
    
    public abstract void onDoubleClick ();
    
}
