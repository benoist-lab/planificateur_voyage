package com.planificateurVoyage.ui.metier.voyage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.RowSorterEvent;

import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.Voyage;
import com.planificateurVoyage.service.AdresseService;
import com.planificateurVoyage.service.StatutVoyageService;
import com.planificateurVoyage.service.VoyageService;
import com.planificateurVoyage.ui.MainDialog;
import com.planificateurVoyage.ui.model.AdresseTableModel;
import com.planificateurVoyage.ui.model.StatutVoyageComboBoxModel;
import com.planificateurVoyage.ui.model.VoyageTableModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JComboBoxUI;
import com.planificateurVoyage.ui.tool.JLabelEspace;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTableUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;
import com.planificateurVoyage.ui.tool.JButtonUI.TAILLE;
import com.planificateurVoyage.ui.tool.adpater.DoubleClickTableMouseAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VoyageConsultationPanel extends AbstractPanelTitle {
	
	private final VoyageService voyageService;	
	private final StatutVoyageService statutVoyageService;
	
	private final MainDialog dialogMain;

	//UI
	private JPanelUI panneauCentral;
	private JPanelUI panneauCritere;
	
	private final VoyageTableModel voyageTableModel;
    private JTableUI tableVoyage;	
	
	private JTextFieldUI tfCritereLibelle;	
	private JTextFieldUI tfCritereAnnee;
	private final StatutVoyageComboBoxModel statutVoyageComboBoxModel;
	private JComboBoxUI cbCritereStatutVoyage;	
	private JTextFieldUI tfCritereNbPersonne;    
    private JButtonUI bRecherche;
	

	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			panneauCentral=new JPanelUI (new BorderLayout ());

			panneauCentral.add(getPanneauCritere (),BorderLayout.NORTH);
			panneauCentral.add(new JScrollPaneUI(getTableVoyage ()),BorderLayout.CENTER);
		}
		return panneauCentral;
	}
	
	

	protected JPanel getPanneauCritere () {
		if (panneauCritere==null) {
			panneauCritere=new JPanelUI ();

			panneauCritere.setBorder (BorderFactory.createTitledBorder("Recherche"));
			
			panneauCritere.setLayout(new FlowLayout (FlowLayout.LEADING));
			

			panneauCritere.add(new JLabelUI("Libell\u00E9:"));
			panneauCritere.add(getCritereLibelle ());

			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(new JLabelUI("Ann\u00E9e:"));
			panneauCritere.add(getCritereAnnee ());
			
			panneauCritere.add(new JLabelEspace ());

			panneauCritere.add(new JLabelUI("Statut:"));
			panneauCritere.add(getCritereStatut ());

			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(new JLabelUI("Nb personne:"));
			panneauCritere.add(getCritereNbPersonne ());

			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(getBoutonRecherche ());
			

		}
		return panneauCritere;
	}

	

    protected JTable getTableVoyage () {
		if (tableVoyage==null) {
			tableVoyage = new JTableUI(voyageTableModel);

			tableVoyage.addMouseListener(new DoubleClickTableMouseAdapter() {
				@Override
				public void onDoubleClick() {
					modifierVoyage (((VoyageTableModel)getTableVoyage ().getModel()).getData(getRowClick ()));
				}
			});
		}
		return tableVoyage;
	}

    
    protected JTextField getCritereLibelle () {
    	if (tfCritereLibelle==null) {
    		tfCritereLibelle=new JTextFieldUI ();
    	}
    	
    	return tfCritereLibelle;	
    }
    
    
    protected JTextField getCritereAnnee () {
    	if (tfCritereAnnee==null) {
    		tfCritereAnnee=new JTextFieldUI ();
    		
    		
    		/// MASK ANNEEE
    		
    		
    	}
    	
    	return tfCritereAnnee;	
    }
	
    private JComboBox getCritereStatut () {
    	
    	if (cbCritereStatutVoyage==null) {
    		cbCritereStatutVoyage=new JComboBoxUI (statutVoyageComboBoxModel);
    	}
    	
    	return cbCritereStatutVoyage;
    }
	

    protected JTextField getCritereNbPersonne () {
    	if (tfCritereNbPersonne==null) {
    		tfCritereNbPersonne=new JTextFieldUI ();
    		
    		// ACCEPTER QUE DES ENTIERS
    		
    	}
    	
    	return tfCritereNbPersonne;	
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
    
    
    
	
	protected void init () {
		initUI ("Consultation d'un voyage");
		
		initialiserModel ();
	}
	
	public void initialiserModel () {
		setModel (voyageService.readVoyages());

		statutVoyageComboBoxModel.setModel (statutVoyageService.readStatutVoyages());
	}
	
	
	protected void setModel (List<Voyage> listVoyage) {
		

		tableVoyage.setRowSorter(null);
		
		voyageTableModel.setModel(listVoyage);
		
		tableVoyage.setAutoCreateRowSorter(true);
		tableVoyage.sorterChanged(new RowSorterEvent (tableVoyage.getRowSorter()));

		
	}
	
	
	
	protected void recherecher ()
	{

	}

	protected void modifierVoyage (Voyage voyage) {

		dialogMain.getVoyageModificationPanel().setVoyage(voyage);
		
		dialogMain.voirPanneauPrincipal (MainDialog.VOYAGE_MODIFICATION);

	}

	

}