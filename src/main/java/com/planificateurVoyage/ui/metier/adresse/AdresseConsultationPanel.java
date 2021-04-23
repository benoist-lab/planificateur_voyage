package com.planificateurVoyage.ui.metier.adresse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.RowSorterEvent;

import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.service.AdresseService;
import com.planificateurVoyage.ui.MainDialog;
import com.planificateurVoyage.ui.model.AdresseTableModel;
import com.planificateurVoyage.ui.model.PersonneTableModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.JButtonUI;
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
public class AdresseConsultationPanel extends AbstractPanelTitle {
	
	private final  AdresseService adresseService;
	
	private final MainDialog dialogMain;

	//UI
	private JPanelUI panneauCentral;
	private JPanelUI panneauCritere;

	private final AdresseTableModel adresseTableModel;	
    private JTableUI tableAdresse;	
    
    private JTextFieldUI tfCritereLibelle;
    private JTextFieldUI tfCritereCodePostal;
    private JTextFieldUI tfCritereVille;
    private JTextFieldUI tfCriterePays;
    
    private JButtonUI bRecherche;
    
    
    
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			panneauCentral=new JPanelUI (new BorderLayout ());

			panneauCentral.add(getPanneauCritere (),BorderLayout.NORTH);
			panneauCentral.add(new JScrollPaneUI(getTableAdresse ()),BorderLayout.CENTER);
		}
		return panneauCentral;
	}
	
	protected JPanel getPanneauCritere () {
		if (panneauCritere==null) {
			panneauCritere=new JPanelUI ();

			panneauCritere.setBorder (BorderFactory.createTitledBorder("Recherche"));
			
			panneauCritere.setLayout(new FlowLayout (FlowLayout.LEADING));
			
			panneauCritere.add(new JLabelUI("Libelle:"));
			panneauCritere.add(getCritereLibelle ());

			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(new JLabelUI("Code postal:"));
			panneauCritere.add(getCritereCodePostal ());
			
			panneauCritere.add(new JLabelEspace ());

			panneauCritere.add(new JLabelUI("Ville:"));
			panneauCritere.add(getCritereVille ());

			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(new JLabelUI("Pays:"));
			panneauCritere.add(getCriterePays ());

			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(getBoutonRecherche ());
		}
		return panneauCritere;
	}
    
    protected JTable getTableAdresse () {
		if (tableAdresse==null) {
			tableAdresse = new JTableUI(adresseTableModel);

			tableAdresse.addMouseListener(new DoubleClickTableMouseAdapter() {
				@Override
				public void onDoubleClick() {
		        	modifierAdresse (((AdresseTableModel)getTableAdresse ().getModel()).getData(getRowClick ()));
				}
			});
		}
		return tableAdresse;
	}
    
    protected JTextField getCritereLibelle () {
    	if (tfCritereLibelle==null) {
    		tfCritereLibelle=new JTextFieldUI ();
    	}
    	
    	return tfCritereLibelle;	
    }

    
    protected JTextField getCritereCodePostal () {
    	if (tfCritereCodePostal==null) {
    		tfCritereCodePostal=new JTextFieldUI ();
    	}
    	
    	return tfCritereCodePostal;	
    }
    
    protected JTextField getCritereVille () {
    	if (tfCritereVille==null) {
    		tfCritereVille=new JTextFieldUI ();
    	}
    	
    	return tfCritereVille;	
    }
    
    protected JTextField getCriterePays () {
    	if (tfCriterePays==null) {
    		tfCriterePays=new JTextFieldUI ();
    	}
    	
    	return tfCriterePays;	
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
		initUI ("Consultation d'une adresse");

		initialiserModel ();
	}
	
	
	public void initialiserModel () {
		setModel (adresseService.readAdresses());
	}

	
	protected void setModel (List<Adresse> listAdresse) {

		tableAdresse.setRowSorter(null);
		
		adresseTableModel.setModel(listAdresse);
		
		tableAdresse.setAutoCreateRowSorter(true);
		tableAdresse.sorterChanged(new RowSorterEvent (tableAdresse.getRowSorter()));
	}
	
	

	protected void recherecher ()
	{
		Adresse adresseCritere=new Adresse ();
		CodePostal codePostal=new CodePostal ();
		Pays pays=new Pays ();
		
		adresseCritere.setLibelle(getCritereLibelle ().getText());
		
		codePostal.setCodePostal(getCritereCodePostal().getText());
		codePostal.setVille(getCritereVille().getText());
		
		pays.setPays(getCriterePays().getText());
		
		codePostal.setPays(pays);

		adresseCritere.setCodePostal(codePostal);
		
		setModel (adresseService.rechercherAdresse(adresseCritere));
	}

	

	protected void modifierAdresse (Adresse adresse) {

		dialogMain.getAdresseModificationPanel().setAdresse(adresse);
		
		dialogMain.voirPanneauPrincipal (MainDialog.ADRESSE_MODIFICATION);

	}
	
}