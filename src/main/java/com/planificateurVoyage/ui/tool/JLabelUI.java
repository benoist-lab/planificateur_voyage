package com.planificateurVoyage.ui.tool;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.UIManager;

public class JLabelUI extends JLabel {

	public JLabelUI () {
		super ();
		setSize (UIManager.getDimension(("Label.dimension")));
	}
	
	public JLabelUI (String text) {
		this();
		setText(text);
	}
	
	public JLabelUI (String text, Font font) {
		this(text);

		setFont(font);
	}
	
	public JLabelUI (String text, Font font, Dimension dimension) {
		this(text,font);
		setSize (dimension);
	}

}
