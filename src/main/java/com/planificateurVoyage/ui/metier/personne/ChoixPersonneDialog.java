package com.planificateurVoyage.ui.metier.personne;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JTable;

import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.service.AdresseService;
import com.planificateurVoyage.service.PersonneService;
import com.planificateurVoyage.ui.model.AdresseTableModel;
import com.planificateurVoyage.ui.model.PersonneTableModel;
import com.planificateurVoyage.ui.tool.JDialogUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTableUI;
import com.planificateurVoyage.ui.tool.adpater.DoubleClickTableMouseAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChoixPersonneDialog extends JDialogUI {


	private final PersonneService personneService;
	private final PersonneTableModel personneTableModel;
	private JTableUI tablePersonne;
	
	private Personne personneChoisie=null;
	
	
	public Personne getPersonneChoisie() {
		return personneChoisie;
	}

	public void setPersonneChoisie(Personne personneChoisie) {
		this.personneChoisie = personneChoisie;
	}
	
	protected JTable getTablePersonne () {
		
		if (tablePersonne==null) {
			tablePersonne= new JTableUI (personneTableModel);
			
			tablePersonne.addMouseListener(new DoubleClickTableMouseAdapter() {

				@Override
				public void onDoubleClick() {
					setPersonneChoisie (((PersonneTableModel)getTablePersonne ().getModel()).getData(getRowClick ()));
					setVisible (false);
				}
			});
		}
		
		return tablePersonne;
	}
	
	public void initialiserModel () {

		setTitle ("choisissez une personne");
		
		setLocation (50,50);
		setSize (1000,500);
		setModal(true);
		
		addComponentListener(new ComponentAdapter () {
			@Override
			public void componentShown(ComponentEvent e) {
				personneChoisie=null;
			}
		});
		
		getContentPane().add(new JScrollPaneUI(getTablePersonne ()));
	}
}
