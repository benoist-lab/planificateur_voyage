package com.planificateurVoyage.ui.metier.activite;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.TableColumnModel;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.model.request.ActiviteRechercheRequest;
import com.planificateurVoyage.service.ActiviteService;
import com.planificateurVoyage.service.CategorieService;
import com.planificateurVoyage.ui.MainDialog;
import com.planificateurVoyage.ui.model.ActiviteTableModel;
import com.planificateurVoyage.ui.model.CategorieComboBoxModel;
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
public class ActiviteConsultationPanel extends AbstractPanelTitle {
	
	//Service
	private final ActiviteService activiteService;
	private final CategorieService categorieService;
	
	private final MainDialog dialogMain;
	
	
	//UI
	private JPanelUI panneauCentral;
	private JPanelUI panneauCritere;

	private final ActiviteTableModel activiteTableModel;	
    private JTableUI tableActivite;	
    
	private final CategorieComboBoxModel categorieComboBoxModel;
	private JComboBox cbCritereCategorie;
    private JTextFieldUI tfCritereLibelle;
    private JTextFieldUI tfCritereAdresse;
    
    private JButtonUI bRecherche;
    
	
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			panneauCentral=new JPanelUI (new BorderLayout ());

			panneauCentral.add(getPanneauCritere (),BorderLayout.NORTH);
			panneauCentral.add(new JScrollPaneUI(getTableActivite ()),BorderLayout.CENTER);
		}
		return panneauCentral;
	}
	
	
	protected JPanel getPanneauCritere () {
		if (panneauCritere==null) {
			panneauCritere=new JPanelUI ();

			panneauCritere.setBorder (BorderFactory.createTitledBorder("Recherche"));
			
			panneauCritere.setLayout(new FlowLayout (FlowLayout.LEADING));
			
			panneauCritere.add(new JLabelUI("Cat\u00E9gorie:"));
			panneauCritere.add(getCritereCategorie());
			
			panneauCritere.add(new JLabelUI("Libelle:"));
			panneauCritere.add(getCritereLibelle ());

			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(new JLabelUI("Adresse:"));
			panneauCritere.add(getCritereAdresse ());
			
			panneauCritere.add(new JLabelEspace ());
			
			panneauCritere.add(getBoutonRecherche ());
		}
		return panneauCritere;
	}
    
    protected JTable getTableActivite () {
		if (tableActivite==null) {
			tableActivite = new JTableUI(activiteTableModel);

			tableActivite.getColumnModel().getColumn(0).setMaxWidth(25);
			tableActivite.getColumnModel().getColumn(3).setMinWidth(500);
			tableActivite.getColumnModel().getColumn(4).setMaxWidth(60);
			tableActivite.getColumnModel().getColumn(5).setMaxWidth(60);

			tableActivite.addMouseListener(new DoubleClickTableMouseAdapter() {
				@Override
				public void onDoubleClick() {
		        	modifierActivite (((ActiviteTableModel)getTableActivite ().getModel()).getData(getRowClick ()));
				}
			});

		}
		
		return tableActivite;
	}
    
    protected JComboBox getCritereCategorie () {
    	if (cbCritereCategorie==null) {
    		cbCritereCategorie=new JComboBoxUI (categorieComboBoxModel);
    	}
    	
    	return cbCritereCategorie;
    }
	
	protected JTextField getCritereLibelle () {
		if (tfCritereLibelle==null) {
			tfCritereLibelle=new JTextFieldUI ();
		}
		
		return tfCritereLibelle;
	}

	protected JTextField getCritereAdresse () {
		if (tfCritereAdresse==null) {
			tfCritereAdresse=new JTextFieldUI ();
		}
		
		return tfCritereAdresse;
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
		initUI ("Consultation d'une activit\u00E9");

		initialiserModel ();
	}
		
	public void initialiserModel () {
		setModel (activiteService.readActivites());
		
		categorieComboBoxModel.setModel (categorieService.readCategories());
		
		Categorie categorie=new Categorie ();
		categorie.setId((long) 0);
		categorieComboBoxModel.insertElementAt(categorie, 0);
		categorieComboBoxModel.setSelectedItem(categorie);
	}
	
	protected void setModel (List<Activite> listActivite) {

		tableActivite.setRowSorter(null);
		
		activiteTableModel.setModel(listActivite);
		
		tableActivite.setAutoCreateRowSorter(true);
		tableActivite.sorterChanged(new RowSorterEvent (tableActivite.getRowSorter()));
	}
	

	protected void recherecher ()
	{
		ActiviteRechercheRequest activiteCritere=new ActiviteRechercheRequest ();
		activiteCritere.setCategorie((Categorie) getCritereCategorie().getSelectedItem());
		activiteCritere.setLibelle(getCritereLibelle().getText());
		activiteCritere.setAdresse(getCritereAdresse().getText());
		
		setModel (activiteService.rechercherActivite(activiteCritere));
		
	}

	protected void modifierActivite (Activite activite) {

		dialogMain.getActiviteModificationPanel().setActivite(activite);
		
		dialogMain.voirPanneauPrincipal (MainDialog.ACTIVITE_MODIFICATION);

	}

	

}