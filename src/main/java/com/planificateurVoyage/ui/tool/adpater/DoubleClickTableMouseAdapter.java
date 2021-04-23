package com.planificateurVoyage.ui.tool.adpater;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public abstract class DoubleClickTableMouseAdapter extends MouseAdapter{
	
	private int rowClick;
	public int getRowClick () {
		return rowClick;
	}

    public void mousePressed(MouseEvent mouseEvent) {
        JTable table =(JTable) mouseEvent.getSource();

        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
        	rowClick=table.convertRowIndexToModel(table.rowAtPoint(mouseEvent.getPoint()));
        	onDoubleClick ();
        }
    }
    
    public abstract void onDoubleClick ();
    
}
