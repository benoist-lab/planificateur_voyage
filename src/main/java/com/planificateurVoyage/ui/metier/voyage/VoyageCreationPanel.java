package com.planificateurVoyage.ui.metier.voyage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.PersonneVoyage;
import com.planificateurVoyage.model.StatutVoyage;
import com.planificateurVoyage.model.Voyage;
import com.planificateurVoyage.model.request.PersonneCreateRequest;
import com.planificateurVoyage.model.request.VoyageCreateRequest;
import com.planificateurVoyage.service.PersonneService;
import com.planificateurVoyage.service.StatutVoyageService;
import com.planificateurVoyage.service.VoyageService;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.ui.MainDialog;
import com.planificateurVoyage.ui.MainFrame;
import com.planificateurVoyage.ui.metier.personne.ChoixPersonneDialog;
import com.planificateurVoyage.ui.model.CategorieComboBoxModel;
import com.planificateurVoyage.ui.model.PersonneTableModel;
import com.planificateurVoyage.ui.model.PersonneTableModelSuppression;
import com.planificateurVoyage.ui.model.StatutVoyageComboBoxModel;
import com.planificateurVoyage.ui.model.VoyageTableModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.DocumentFilterTextRegex;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JComboBoxUI;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelFormulaire;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTableButtonRenderer;
import com.planificateurVoyage.ui.tool.JTableUI;
import com.planificateurVoyage.ui.tool.JTextAreaUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;
import com.planificateurVoyage.ui.tool.adpater.ClickTableMouseAdapter;
import com.planificateurVoyage.ui.tool.adpater.DoubleClickTableMouseAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VoyageCreationPanel extends AbstractPanelTitle {
	
	public static final String MODE_CREATION="MODE_CREATION";
	public static final String MODE_MODIFICATION="MODE_MODIFICATION";
	

	private final VoyageService voyageService;	
	private final StatutVoyageService statutVoyageService;
	private final VoyageTableModel voyageTableModel;
	private final PersonneService personneService;

	private JTextFieldUI tfLibelle;	
	private JTextAreaUI taDescription;
	private final StatutVoyageComboBoxModel statutVoyageComboBoxModel;
	private JComboBoxUI cbStatutVoyage;
	
	private PersonneTableModel personneTableModelVoyage;	
    private JTableUI tablePersonne;	
    private JButtonUI boutonAddPersonne;
	
	private JPanelFormulaire panneauCentral;
	private JPanelUI panneauControl;
	
	private final ChoixPersonneDialog choixPersonneDialog;
	private JDialog dialogMere=null;
	
	private String mode=MODE_CREATION;
	private Voyage voyageData=null;
	
	public void setMode (String mode) {
		this.mode=mode;
	}
	
	public String getMode () {
		return mode;
	}
	
	public void setVoyage (Voyage voyage) {
		voyageData=voyage;
		
		setTitre("Modification du voyage "+voyageData.getId());
		
		getLibelle ().setText(voyage.getLibelle());
		
		getDescription ().setText(voyage.getDescription());
		
		getStatutVoyage ().setSelectedItem(voyage.getStatut());
		

		setVoyagePersonne (voyage.getPersonneVoyage());
	}
	
	protected void setVoyagePersonne (Set<PersonneVoyage> personneVoyage) {
		getPersonneModel ().clearData();
		for (PersonneVoyage pers:personneVoyage) {
			
			getPersonneModel ().addData(personneService.readPersonne(pers.getPersonneVoyageKey().getPersonneId()));
		}
	}
	
	public Voyage getVoyage () {
		return voyageData;
	}
	
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			
			panneauCentral=new JPanelFormulaire (new int [] {
				((Dimension)UIManager.get(("Label.dimension"))).width,
				((Dimension)UIManager.get(("TextField.dimension"))).width+20,
				((Dimension)UIManager.get(("Label.dimension"))).width,
				((Dimension)UIManager.get(("TextField.dimension"))).width+50,
				((Dimension)UIManager.get(("Button.dimension"))).width
			});

			panneauCentral.add(new JLabelUI("libell\u00E9:"),0,0);
			panneauCentral.add(getLibelle (),0,1);

			panneauCentral.add(new JLabelUI("statut:"),0,2);
			panneauCentral.add(getStatutVoyage (),0,3);
			
			panneauCentral.add(new JLabelUI("Description:"),1,0);
			
			JScrollPane scrollPaneDescription=new JScrollPaneUI(getDescription ());
			scrollPaneDescription.setSize (UIManager.getDimension(("TextArea.dimension")));
			scrollPaneDescription.setBorder (BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			panneauCentral.add(scrollPaneDescription,1,1);

			
			panneauCentral.add(new JLabelUI("Personne:"),4,0);
			
			JScrollPane scrollPanePersonne=new JScrollPaneUI(getTablePersonne ());
			scrollPanePersonne.setSize (new Dimension (((JPanelFormulaire)getPanneauCentral ()).getLargeurTotal(),(((Dimension) UIManager.get("Label.dimension")).height)*5+10));
			scrollPanePersonne.setBorder (BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			panneauCentral.add(scrollPanePersonne,4,1);	
			
			JPanelUI panneauBoutonAddPersonne=new JPanelUI (new FlowLayout (FlowLayout.RIGHT));			
			panneauBoutonAddPersonne.setSize (new Dimension (((JPanelFormulaire)getPanneauCentral ()).getLargeurTotal(),((Dimension) UIManager.get("Button.dimension")).height+10));
			panneauBoutonAddPersonne.add(getBoutonAddPersonne());			
			panneauCentral.add(panneauBoutonAddPersonne,8,0);
			
			
			
			panneauCentral.add(getPanneauControl (),10,0);
		}
		return panneauCentral;
	}
	
	public JPanel getPanneauControl () {
		if (panneauControl==null) {
			panneauControl=new JPanelUI (new FlowLayout (FlowLayout.RIGHT));
			
			panneauControl.setSize (new Dimension (((JPanelFormulaire)getPanneauCentral ()).getLargeurTotal(),((Dimension) UIManager.get("Button.dimension")).height+10));
			
			JButtonUI bOk=new JButtonUI ("Ok");
			bOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					enregistrer ();
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
	
	protected JTextField getLibelle () {
		if (tfLibelle==null) {
			tfLibelle=new JTextFieldUI ();
			
		}
		
		return tfLibelle;
	}
	
	protected JTextAreaUI getDescription () {
		if (taDescription==null) {
			taDescription=new JTextAreaUI ();
		}
		
		return taDescription;
		
	}
	
	private JComboBox getStatutVoyage () {
		if (cbStatutVoyage==null) {
			cbStatutVoyage=new JComboBoxUI (statutVoyageComboBoxModel);
		}
		
		return cbStatutVoyage;
	}
	
	protected PersonneTableModel getPersonneModel () {
		if (personneTableModelVoyage==null) {
			personneTableModelVoyage = new PersonneTableModelSuppression ();
			personneTableModelVoyage.initialiserModel ();
		}
		
		return personneTableModelVoyage;
	}
	
    protected JTable getTablePersonne () {
		if (tablePersonne==null) {
			
			tablePersonne = new JTableUI(getPersonneModel ());
	
			tablePersonne.getColumn(" ").setCellRenderer(new JTableButtonRenderer());
			
			tablePersonne.addMouseListener(new ClickTableMouseAdapter() {
				@Override
				public void onClick() {

					if (getColumnClick()==3) {
						supprimerPersonne (((PersonneTableModel)getTablePersonne ().getModel()).getData(getRowClick ()));
					}
				}
			});
		}
		
		return tablePersonne;
	}
    
    public JButtonUI getBoutonAddPersonne () {
    	if (boutonAddPersonne==null) {
    		boutonAddPersonne=new JButtonUI("ajouter");
    		
    		boutonAddPersonne.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ajouterPersonne ();
				}
    			
    		});
    	}
    	
    	return boutonAddPersonne;
    }
    

	public void setDialogMere (JDialog dialogMere) {
		this.dialogMere=dialogMere;
	}
	
	public JDialog getDialogMere () {
		return dialogMere;
	}
	
	protected void init () {
		if (getMode ().equals(MODE_CREATION)) {
			initUI ("Cr\u00E9ation d'un voyage");
		}else if (getMode ().equals(MODE_MODIFICATION)){
			initUI ("Modification d'un voyage");
		}else {
			throw new RuntimeException ("mode inconnu: "+getMode ());
		}
		
		initialiserModel ();
	}
	
	
	
	public void initialiserModel () {
		statutVoyageComboBoxModel.setModel (statutVoyageService.readStatutVoyages());
	}
	
	private boolean isFormulaireValide () {
		return !getLibelle  ().getText().isEmpty();
	}
	

	protected Voyage getSaisie () {
		Voyage voyage=new Voyage ();

		if (getMode ().equals(MODE_MODIFICATION)) {
			voyage.setId(getVoyage () .getId());
		}
		

		voyage.setLibelle(getLibelle ().getText());
		voyage.setStatut((StatutVoyage) getStatutVoyage().getSelectedItem());
		voyage.setDescription(getDescription ().getText());


		return voyage;
	}

    private static final Logger logger = LoggerFactory.getLogger(VoyageCreationPanel.class);
    
	
	private void enregistrer () {
		
		logger.info ("enregistrer");

		Voyage voyage;
		VoyageCreateRequest request;
		
		if (isFormulaireValide ()) {
			try {
				Voyage voyageSaisie=getSaisie ();
				
				request=new VoyageCreateRequest ();
				
				request.setId(voyageSaisie.getId());
				request.setLibelle(voyageSaisie.getLibelle ());
				request.setStatut(voyageSaisie.getStatut());
				request.setDescription(voyageSaisie.getDescription ());

				request.setPersonne(personneTableModelVoyage.getListData());
				
				voyage=voyageService.createVoyage(request);

				if (getMode ().equals(MODE_MODIFICATION)) {
					voyageTableModel.replaceData(voyage);
					
					setVoyagePersonne (voyage.getPersonneVoyage());
					
				}else {
					voyageTableModel.addData(voyage);
				}
				
				JOptionPane.showMessageDialog(this,"donn\u00E9e enregistr\u00E9e.","Message",JOptionPane.INFORMATION_MESSAGE);
				
				if (getDialogMere ()!=null) {
					getDialogMere ().setVisible(false);
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this,"le formulaire est incomplet.","Warning",JOptionPane.WARNING_MESSAGE);
		}
		
	}

	private void annuler () {
		
		if (getDialogMere ()!=null) {
			getDialogMere ().setVisible(false);
		}
	}
	

	private void ajouterPersonne () {
		
		choixPersonneDialog.setVisible(true);
		
		if (choixPersonneDialog.getPersonneChoisie()!=null) {
			
			if (!getPersonneModel().containtData (choixPersonneDialog.getPersonneChoisie())) {
				getPersonneModel().addData(choixPersonneDialog.getPersonneChoisie());
			}
		}

	}
	
	private void supprimerPersonne (Personne personne) {
		
		((PersonneTableModel)getTablePersonne ().getModel()).removeData(personne);
		
	}


}
