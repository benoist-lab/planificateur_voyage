package com.planificateurVoyage.ui.tool;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.UIManager;

import com.planificateurVoyage.ui.configuration.UIConfiguration;

import lombok.RequiredArgsConstructor;

public class JButtonUI extends JButton{

	public static enum TAILLE{
		TAILLE_DEFAULT,
		TAILLE_LARGE,
		TAILLE_ICONE
	}

	
	public JButtonUI() {
		super ();
		setTaille (TAILLE.TAILLE_DEFAULT);
		
		
	}

	public JButtonUI(String text) {
		this ();
		
		setText (text);
	}
	
	public JButtonUI(Icon icone) {
		this();
		setIcon(icone);
		setTaille (TAILLE.TAILLE_ICONE);
	}
	
	public void setTaille (TAILLE taille) {
		switch(taille) {
			case TAILLE_DEFAULT:
				setPreferredSize((Dimension) UIManager.get("Button.dimension"));
				break;
			case TAILLE_LARGE:
				setPreferredSize((Dimension) UIManager.get("Button.dimension.large"));
				break;
				
			case TAILLE_ICONE:
				setPreferredSize((Dimension) UIManager.get("Button.dimension.icon"));
				break;
		}
	}
}
