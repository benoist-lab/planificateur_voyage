package com.planificateurVoyage.ui.metier.activite;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.model.request.ActiviteCreateRequest;
import com.planificateurVoyage.service.ActiviteService;
import com.planificateurVoyage.service.CategorieService;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.ui.metier.adresse.ChoixAdresseDialog;
import com.planificateurVoyage.ui.model.ActiviteTableModel;
import com.planificateurVoyage.ui.model.CategorieComboBoxModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.DocumentFilterTextRegex;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JCheckBoxUI;
import com.planificateurVoyage.ui.tool.JComboBoxUI;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelFormulaire;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTextAreaUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;
import com.planificateurVoyage.ui.tool.adpater.FocusBorderAdpater;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActiviteCreationPanel extends AbstractPanelTitle {
	
	public static final String MODE_CREATION="MODE_CREATION";
	public static final String MODE_MODIFICATION="MODE_MODIFICATION";
	
	//Service
	private final ActiviteService activiteService;
	private final CategorieService categorieService;
	

	private final ActiviteTableModel activiteTableModel;	

	
	private JPanelFormulaire panneauCentral;
	private JPanelUI panneauControl;
    
	private final ChoixAdresseDialog choixAdresseDialog;

	private final CategorieComboBoxModel categorieComboBoxModel;
	private JComboBoxUI cbCategorie;
	
    private JTextFieldUI tfLibelle;
    
	private Adresse adresseValue=null;	// memoire de l'adresse en ligne
    private JTextFieldUI tfadresse;
    
    private JTextFieldUI tfCout;
    private JCheckBoxUI cbForfaitaire;
	private JTextAreaUI taDescription;
	
	
	
	
	private JDialog dialogMere=null;
	
	private String mode=MODE_CREATION;
	private Activite activiteData=null;
	
	
	public void setMode (String mode) {
		this.mode=mode;
	}
	
	public String getMode () {
		return mode;
	}
	
	public void setActivite (Activite activite) {

		activiteData=activite;
		
		setTitre("Modification de l'activit\u00E9 "+activite.getId());
		
		getCategorie ().setSelectedItem(activite.getCategorie());
		getLibelle().setText(activite.getLibelle());
		setAdresseValue (activite.getAdresse());
		getCout ().setText(Double.toString(activite.getCout()));
		getForfaitaire ().setSelected(activite.isForfaitaire());
		getDescription ().setText(activite.getDescription());
	}
	
	public Activite getActivite () {
		return activiteData;
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
			
			panneauCentral.add(new JLabelUI("Cat\u00E9gorie"),0,0);
			panneauCentral.add(getCategorie (),0,1);

			panneauCentral.add(new JLabelUI("libelle"),1,0);
			panneauCentral.add(getLibelle (),1,1);

			panneauCentral.add(new JLabelUI("adresse:"),2,0);
			panneauCentral.add(getAdresse (),2,1);
			
			JButtonUI bModifierAdresse=new JButtonUI ("Choisir");
			bModifierAdresse.setSize((Dimension) UIManager.get("Button.dimension"));
			bModifierAdresse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					choisirAdresse ();
				}				
			});
			panneauCentral.add(bModifierAdresse,2,4);

			panneauCentral.add(new JLabelUI("cout:"),3,0);
			panneauCentral.add(getCout (),3,1);
			panneauCentral.add(new JLabelUI("Euros"),3,2);

			panneauCentral.add(getForfaitaire (),3,3);
			
			panneauCentral.add(new JLabelUI("Description:"),4,0);
			
			JScrollPane scrollPaneDescription=new JScrollPaneUI(getDescription ());
			scrollPaneDescription.setSize (UIManager.getDimension(("TextArea.dimension")));
			scrollPaneDescription.setBorder (BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			panneauCentral.add(scrollPaneDescription,4,1);
			
			panneauCentral.add(getPanneauControl (),7,0);
			
			
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
			
			if (getMode ().equals(MODE_MODIFICATION)) {
				JButtonUI bAnnuler=new JButtonUI ("Annuler");
				panneauControl.add(bAnnuler);
				bAnnuler.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						annuler ();
					}				
				});
			}
			
		}
		
		return panneauControl;
	}
	

	protected JTextField getLibelle () {
		if (tfLibelle==null) {
			tfLibelle=new JTextFieldUI ();
		}
		
		return tfLibelle;
	}

	
	public void setAdresseValue (Adresse adresse) {
		adresseValue=adresse;
		
		if (adresseValue==null) {
			getAdresse().setText("");
		}else {
			getAdresse().setText(adresseValue.getAdresseLigne());
		}
	}
	
	public Adresse getAdresseValue () {
		return adresseValue;
	}
	
	protected JTextField getAdresse () {
		if (tfadresse==null) {
			tfadresse=new JTextFieldUI ();
			tfadresse.setSize(UIManager.getDimension("TextField.dimension.large"));
			tfadresse.setEditable(false);

		}
		
		return tfadresse;
	}

	protected JTextField getCout () {
		if (tfCout==null) {
			tfCout=new JTextFieldUI ();

			tfCout.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_NUMERIQUE));
		}
		
		return tfCout;
	} 

	protected JCheckBox getForfaitaire () {
		if (cbForfaitaire==null) {
			cbForfaitaire=new JCheckBoxUI ("Forfaitaire");
		}
		
		return cbForfaitaire;
	}
	

	protected JTextArea getDescription () {
		if (taDescription==null) {
			taDescription=new JTextAreaUI ();
		}
		
		return taDescription;
	}

	
	
	
	

	private JComboBoxUI getCategorie () {
		if (cbCategorie==null) {
			cbCategorie=new JComboBoxUI (categorieComboBoxModel);

		}
		
		return cbCategorie;
	}
    


	public void setDialogMere (JDialog dialogMere) {
		this.dialogMere=dialogMere;
	}
	
	public JDialog getDialogMere () {
		return dialogMere;
	}

	
	// CONSTRUCTEUR
	protected void init () {
		if (getMode ().equals(MODE_CREATION)) {
			initUI ("Cr\u00E9ation d'une activit\u00E9");
		}else if (getMode ().equals(MODE_MODIFICATION)){
			initUI ("Modification d'une activit\u00E9");
		}else {
			throw new RuntimeException ("mode inconnu: "+getMode ());
		}
		
		initialiserModel ();
	}
	

	public void initialiserModel () {

		categorieComboBoxModel.setModel (categorieService.readCategories());
		
		Categorie categorie=new Categorie ();
		categorie.setId((long) 0);
		categorieComboBoxModel.insertElementAt(categorie, 0);
		categorieComboBoxModel.setSelectedItem(categorie);
	}
	




	protected Activite getSaisie () {
		Activite activite=new Activite ();

		if (getMode ().equals(MODE_MODIFICATION)) {
			activite.setId(getActivite () .getId());
		}

		activite.setCategorie((Categorie) getCategorie().getSelectedItem());
		activite.setLibelle(getLibelle ().getText());
		activite.setAdresse(getAdresseValue ());

		try {
			activite.setCout(new Double(getCout().getText()));
		} catch(NumberFormatException nfe) {
			activite.setCout(0);
		}
		
		activite.setForfaitaire(getForfaitaire().isSelected());
		activite.setDescription(getDescription().getText());

		return activite;
	}
	
	private void enregistrer () {

		Activite activite;
		ActiviteCreateRequest request;
		
			try {

				Activite act= getSaisie ();

				activiteService.validerActivite(act);

				request=new ActiviteCreateRequest ();
				
				request.setId(act.getId());
				request.setCategorie(act.getCategorie());
				request.setLibelle(act.getLibelle());
				request.setAdresse(act.getAdresse());
				request.setCout(act.getCout());
				request.setForfaitaire(act.isForfaitaire());
				request.setDescription(act.getDescription());

				activite=activiteService.createActivite(request);

				if (getMode ().equals(MODE_MODIFICATION)) {
					activiteTableModel.replaceData (activite);
				}
				else {
					activiteTableModel.addData(activite);
				}
				
				JOptionPane.showMessageDialog(this,"donn\u00E9e enregistr\u00E9e.","Message",JOptionPane.INFORMATION_MESSAGE);
				
				if (getDialogMere ()!=null) {
					getDialogMere ().setVisible(false);
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		
	}

	private void annuler () {
		
		if (getDialogMere ()!=null) {
			getDialogMere ().setVisible(false);
		}
	}
	
	private void choisirAdresse () {
		choixAdresseDialog.setVisible(true);
		
		setAdresseValue (choixAdresseDialog.getAdresseChoisie());
	}

	

}
