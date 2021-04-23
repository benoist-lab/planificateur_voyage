package com.planificateurVoyage.ui.metier.personne;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.annotation.Autowired;

import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.service.PersonneService;
import com.planificateurVoyage.ui.MainDialog;
import com.planificateurVoyage.ui.model.PersonneTableModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JButtonUI.TAILLE;
import com.planificateurVoyage.ui.tool.JLabelEspace;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTableUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;
import com.planificateurVoyage.ui.tool.adpater.DoubleClickTableMouseAdapter;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PersonneConsultationPanel extends AbstractPanelTitle {
	
	//Service
	private final PersonneService personneService;
	
	private final MainDialog dialogMain;
	
	//UI
	private JPanelUI panneauCentral;
	private JPanelUI panneauCritere;

	private final PersonneTableModel personneTableModel;	
    private JTableUI tablePersonne;	
    
    private JTextFieldUI tfCritereNom;
    private JTextFieldUI tfCriterePrenom;
    
    private JButtonUI bRecherche;
    
	
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			panneauCentral=new JPanelUI (new BorderLayout ());

			panneauCentral.add(getPanneauCritere (),BorderLayout.NORTH);
			panneauCentral.add(new JScrollPaneUI(getTablePersonne ()),BorderLayout.CENTER);
		}
		return panneauCentral;
	}
	
	
	protected JPanel getPanneauCritere () {
		if (panneauCritere==null) {
			panneauCritere=new JPanelUI ();

			panneauCritere.setBorder (BorderFactory.createTitledBorder("Recherche"));
			
			panneauCritere.setLayout(new FlowLayout (FlowLayout.LEADING));
			
			panneauCritere.add(new JLabelUI("Nom:"));
			panneauCritere.add(getCritereNom ());

			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(new JLabelUI("Pr\u00E9nom:"));
			panneauCritere.add(getCriterePrenom ());
			
			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(getBoutonRecherche ());
		}
		return panneauCritere;
	}
    
    protected JTable getTablePersonne () {
		if (tablePersonne==null) {
			tablePersonne = new JTableUI(personneTableModel);

			tablePersonne.addMouseListener(new DoubleClickTableMouseAdapter() {
				@Override
				public void onDoubleClick() {
		        	modifierPersonne (((PersonneTableModel)getTablePersonne ().getModel()).getData(getRowClick ()));
				}
			});

		}
		
		return tablePersonne;
	}
	
	protected JTextField getCritereNom () {
		if (tfCritereNom==null) {
			tfCritereNom=new JTextFieldUI ();
		}
		
		return tfCritereNom;
	}

	protected JTextField getCriterePrenom () {
		if (tfCriterePrenom==null) {
			tfCriterePrenom=new JTextFieldUI ();
		}
		
		return tfCriterePrenom;
	}
	
	protected JButton getBoutonRecherche () {
		if (bRecherche==null) {
			bRecherche=new JButtonUI ("Rechercher");
			bRecherche.setTaille(TAILLE.TAILLE_LARGE);
			bRecherche.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					recherecher ();
				}
				
			});
		}
		
		return bRecherche;
	}

	// CONSTRUCTEUR
	protected void init () {
		initUI ("Consultation d'une personne");

		initialiserModel ();
	}
		
	public void initialiserModel () {
		setModel (personneService.readPersonnes());
	}
	
	protected void setModel (List<Personne> listPersonne) {

		tablePersonne.setRowSorter(null);
		
		personneTableModel.setModel(listPersonne);
		
		tablePersonne.setAutoCreateRowSorter(true);
		tablePersonne.sorterChanged(new RowSorterEvent (tablePersonne.getRowSorter()));
	}
	

	protected void recherecher ()
	{
		Personne personneCritere=new Personne ();
		personneCritere.setNom(getCritereNom().getText());
		personneCritere.setPrenom(getCriterePrenom().getText());
		
		setModel (personneService.rechercherPersonne(personneCritere));
		
	}

	protected void modifierPersonne (Personne personne) {

		dialogMain.getPersonneModificationPanel().setPersonne(personne);
		
		dialogMain.voirPanneauPrincipal (MainDialog.PERSONNE_MODIFICATION);

	}


}