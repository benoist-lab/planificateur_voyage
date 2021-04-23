package com.planificateurVoyage.ui.tool;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


import lombok.RequiredArgsConstructor;


public class JPanelUI extends JPanel {


	public JPanelUI() {
		super ();

	}
	
	public JPanelUI (LayoutManager layoutManager) {
		this();
		setLayout (layoutManager);
	}
}
