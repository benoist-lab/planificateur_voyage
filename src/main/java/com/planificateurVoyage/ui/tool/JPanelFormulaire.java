package com.planificateurVoyage.ui.tool;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.planificateurVoyage.ui.MainFrame;

/**
 * gere l'agencement du formulaire en fonction de ligne et colonnes.
 * 
 * @author leopo
 *
 */
public class JPanelFormulaire extends JPanelUI {

    private static final Logger logger = LoggerFactory.getLogger(JPanelFormulaire.class);
    
	private int hauteurLigne=((Dimension)UIManager.get(("Label.dimension"))).height+10;
	
	private int[] longueurColonne=new int [] {
			((Dimension)UIManager.get(("Label.dimension"))).width,
			((Dimension)UIManager.get(("TextField.dimension"))).width
	};
	
	private int marge=10;
	
	public JPanelFormulaire () {
		super (null);
	}


	public JPanelFormulaire (int[] longueurColonne) {
		this ();

		this.longueurColonne=longueurColonne;
	}


	public void add(Component component, int ligne,int colonne) {

		calculerPositionComponent (component, ligne,colonne);
		
		add (component);
	}

	public void calculerPositionComponent (Component component, int ligne,int colonne) {
		
		int x=0;
		for (int i=0;i<colonne;i++) {
			x+=longueurColonne[i];
		}

		component.setLocation(marge+x,marge+ligne*hauteurLigne);
	}
	
	public int getLargeurTotal () {
		int x=0;
		for (int i=0;i<longueurColonne.length;i++) {
			x+=longueurColonne[i];
		}
		
		return marge+x;
	}
		
	
}
