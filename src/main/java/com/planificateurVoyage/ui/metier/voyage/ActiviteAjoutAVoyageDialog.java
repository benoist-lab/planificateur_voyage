package com.planificateurVoyage.ui.metier.voyage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.RowSorterEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.ActiviteVoyage;
import com.planificateurVoyage.model.ActiviteVoyageKey;
import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.request.ActiviteRechercheRequest;
import com.planificateurVoyage.service.ActiviteService;
import com.planificateurVoyage.service.CategorieService;
import com.planificateurVoyage.service.VoyageService;
import com.planificateurVoyage.ui.MainFrame;
import com.planificateurVoyage.ui.model.ActiviteTableModel;
import com.planificateurVoyage.ui.model.ActiviteVoyageTableModel;
import com.planificateurVoyage.ui.model.CategorieComboBoxModel;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JComboBoxUI;
import com.planificateurVoyage.ui.tool.JDialogUI;
import com.planificateurVoyage.ui.tool.JLabelEspace;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelFormulaire;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTableUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;
import com.planificateurVoyage.ui.tool.JTimeDateFieldUI;
import com.planificateurVoyage.ui.tool.JButtonUI.TAILLE;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActiviteAjoutAVoyageDialog extends JDialogUI {
	
	//Service
	private final ActiviteService activiteService;
	private final CategorieService categorieService;
	private final VoyageService voyageService;
	
	private Long voyageId;
	private ActiviteVoyage activiteChoisie=null;
	
	private JPanelFormulaire panneauCentral;
	private JPanelUI panneauCritere;
	private JPanel panneauControl;

	private final ActiviteTableModel activiteTableModel;	
    private JTableUI tableActivite;	
    
	private final CategorieComboBoxModel categorieComboBoxModel;
	private JComboBox cbCritereCategorie;
    private JTextFieldUI tfCritereLibelle;
    private JTextFieldUI tfCritereAdresse;
    
    private JButtonUI bRecherche;
    
    private JTimeDateFieldUI tdDateDebut;
    private JTimeDateFieldUI tdDateFin;
    
    public void setVoyageId (Long id) {
    	voyageId=id;
    }

	public void setActiviteChoisie(ActiviteVoyage activiteChoisie) {
		this.activiteChoisie = activiteChoisie;
	}
	
	public ActiviteVoyage getActiviteChoisie () {
		return activiteChoisie;
	}

	protected JPanelFormulaire getPanneauCentral () {
		if (panneauCentral==null) {
			
			panneauCentral=new JPanelFormulaire ();
			
			panneauCentral.add(getPanneauCritere (),0,0);

			JScrollPaneUI scrollTableActivite=new JScrollPaneUI(getTableActivite ());
			scrollTableActivite.setSize(new Dimension(getSize().width-40, ((Dimension)UIManager.get(("Label.dimension"))).height*6));

			panneauCentral.add(scrollTableActivite,2,0);

			panneauCentral.add(new JLabelUI("Date de d\u00E9but"),6,0);
			panneauCentral.add(getDateDebut (),6,1);
			
			panneauCentral.add(new JLabelUI("Date de fin"),7,0);
			panneauCentral.add(getDateFin (),7,1);
			
			panneauCentral.add(getPanneauControl (),9,0);

			
		}
		return panneauCentral;
	}

	
	protected JPanel getPanneauCritere () {
		if (panneauCritere==null) {
			panneauCritere=new JPanelUI ();
			
			panneauCritere.setSize(new Dimension(getSize().width-40, ((Dimension)UIManager.get(("Label.dimension"))).height+35));

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
    
    
    protected JTimeDateFieldUI getDateDebut () {
    	if (tdDateDebut==null) {
    		tdDateDebut=new JTimeDateFieldUI ();
    		
    		tdDateDebut.setSize(new Dimension(400, ((Dimension)UIManager.get(("Label.dimension"))).height+15));
    	}
    	
    	return tdDateDebut;
    }
    
    protected JTimeDateFieldUI getDateFin () {
    	if (tdDateFin==null) {
    		tdDateFin=new JTimeDateFieldUI ();
    		
    		tdDateFin.setSize(new Dimension(400, ((Dimension)UIManager.get(("Label.dimension"))).height+15));
    	}
    	
    	return tdDateFin;
    }
    
	public JPanel getPanneauControl () {
		if (panneauControl==null) {
			panneauControl=new JPanelUI (new FlowLayout (FlowLayout.RIGHT));
			
			panneauControl.setSize (new Dimension (((JPanelFormulaire)getPanneauCentral ()).getLargeurTotal(),((Dimension) UIManager.get("Button.dimension")).height+10));
			
			JButtonUI bOk=new JButtonUI ("Ok");
			bOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					valider ();
				}				
			});
			panneauControl.add(bOk);
			

			JButtonUI bAnnuler=new JButtonUI ("Annuler");
			panneauControl.add(bAnnuler);
			bAnnuler.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					annuler ();
				}				
			});

			
		}
		
		return panneauControl;
	}
	
    
	
	// CONSTRUCTEUR
	protected void init () {
		setTitle ("Ajout d'une activit\u00E9 \u00E0 un voyage");
		
		setLocation (50,50);
		setSize(new Dimension(1000, 700));
		setModal(true);
		
		addComponentListener(new ComponentAdapter () {
			@Override
			public void componentShown(ComponentEvent e) {
				activiteChoisie=null;
			}
		});
		
		getContentPane ().add(new JScrollPaneUI(getPanneauCentral ()));
		
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
	
	
	private void annuler () {
		setVisible(false);
	}
	
    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
    
	private void valider () {
		
		ActiviteVoyage activite;
		
		try {
			
			activite=new ActiviteVoyage ();
			
			if (getTableActivite ().getSelectedRow()<0) {
				throw new RuntimeException ("veuillez s\u00E9lectionner une activit\u00E9.");
			}
			
			logger.info("VALIDER");
			
			logger.info("Table="+((ActiviteTableModel)getTableActivite ().getModel()).getListData().get(getTableActivite ().getSelectedRow()).getId());

			ActiviteVoyageKey key=new ActiviteVoyageKey ();
			
			key.setActiviteId(((ActiviteTableModel)getTableActivite ().getModel()).getListData().get(getTableActivite ().getSelectedRow()).getId());
			
			key.setVoyageId(voyageId);////////////////////
			
			activite.setActiviteVoyageKey(key);
			
			
			
			activite.setDateDebut(getDateDebut().getDate());
			activite.setDateFin(getDateFin().getDate());
			
			voyageService.validerActivite(activite);
			
			setActiviteChoisie(activite);
			
			
			logger.info("ACTIVITE="+activite.getActiviteVoyageKey().getActiviteId());
			


			setVisible(false);
		
		}catch (Exception e) {
			
			logger.error("exception", e);
			
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
