package com.planificateurVoyage.ui.tool.adpater;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class FocusBorderAdpater implements FocusListener {
	
	private Border ancienBorder=null;

	@Override
	public void focusGained(FocusEvent e) {

		ancienBorder=((JComponent)e.getComponent()).getBorder();
		
		((JComponent)e.getComponent()).setBorder(BorderFactory.createLineBorder((Color) UIManager.get("focus.color")));
	}

	@Override
	public void focusLost(FocusEvent e) {

		if (ancienBorder!=null) {
			((JComponent)e.getComponent()).setBorder(ancienBorder);
		}
		
	}

}
