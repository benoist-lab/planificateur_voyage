package com.planificateurVoyage.ui.tool;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class JTableButtonRenderer extends JButton implements TableCellRenderer {

	  public JTableButtonRenderer() {
	    setOpaque(true);
	  }

	  @Override
	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}