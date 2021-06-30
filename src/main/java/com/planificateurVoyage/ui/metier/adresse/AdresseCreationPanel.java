package com.planificateurVoyage.ui.metier.adresse;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.request.AdresseCreateRequest;
import com.planificateurVoyage.model.request.PersonneCreateRequest;
import com.planificateurVoyage.service.AdresseService;
import com.planificateurVoyage.service.CodePostalService;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.ui.MainDialog;
import com.planificateurVoyage.ui.MainFrame;
import com.planificateurVoyage.ui.metier.referentiel.ReferentielCodePostalAjoutPanel;
import com.planificateurVoyage.ui.model.AdresseTableModel;
import com.planificateurVoyage.ui.model.CodePostalComboBoxModel;
import com.planificateurVoyage.ui.model.PaysComboBoxModel;
import com.planificateurVoyage.ui.model.VilleComboBoxModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.DocumentFilterTextRegex;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JComboBoxUI;
import com.planificateurVoyage.ui.tool.JDialogUI;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelFormulaire;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdresseCreationPanel extends AbstractPanelTitle {
	
	public static final String MODE_CREATION="MODE_CREATION";
	public static final String MODE_MODIFICATION="MODE_MODIFICATION";

    private static final Logger logger = LoggerFactory.getLogger(AdresseCreationPanel.class);
    
	
	private final AdresseService adresseService;

	
	private final ReferentielCodePostalAjoutPanel referentielCodePostalAjoutPanelDialog;
	
	private final AdresseTableModel adresseTableModel;
	
	private JPanelFormulaire panneauCentral;
	private JPanelUI panneauControl;
    
    private JTextFieldUI tfLibelle;
    private JTextFieldUI tfLigne1;
    private JTextFieldUI tfLigne2;
    private JTextFieldUI tfLigne3;
    
	
	private final CodePostalComboBoxModel codePostalComboBoxModel;
	private JComboBox cbCodePostal;
    
	
	private final VilleComboBoxModel villeComboBoxModel;
	private JComboBox cbVille;
	
	private JTextFieldUI tfPays;
	
	private JDialog dialogMere=null;
	
	private String mode=MODE_CREATION;
	private Adresse adresseData=null;
	
	
	public void setMode (String mode) {
		this.mode=mode;
	}
	
	public String getMode () {
		return mode;
	}
	
	public void setAdresse (Adresse adresse) {
		
		adresseData=adresse;
		
		setTitre("Modification de l'adresse "+adresse.getId());
		getLibelle ().setText(adresse.getLibelle());
		getLigne1 ().setText(adresse.getLigne1());
		getLigne2 ().setText(adresse.getLigne2());
		getLigne3 ().setText(adresse.getLigne3());

		getCodePostal().setSelectedItem(adresse.getCodePostal().getCodePostal());
		getVille().setSelectedItem(adresse.getCodePostal().getVille());
		// pays auto remplit
		
	}
	
	public Adresse getAdresse () {
		return adresseData;
	}

	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			
			panneauCentral=new JPanelFormulaire (new int [] {
					((Dimension)UIManager.get(("Label.dimension"))).width,
					((Dimension)UIManager.get(("TextField.dimension"))).width+20,
					((Dimension)UIManager.get(("Label.dimension.small"))).width,
					((Dimension)UIManager.get(("TextField.dimension"))).width+20,
					((Dimension)UIManager.get(("Button.dimension"))).width
			});

			panneauCentral.add(new JLabelUI("libelle"),0,0);
			panneauCentral.add(getLibelle (),0,1);

			panneauCentral.add(new JLabelUI("ligne 1:"),1,0);
			panneauCentral.add(getLigne1 (),1,1);

			panneauCentral.add(new JLabelUI("ligne 2:"),2,0);
			panneauCentral.add(getLigne2 (),2,1);

			panneauCentral.add(new JLabelUI("ligne 3:"),3,0);
			panneauCentral.add(getLigne3 (),3,1);

			
			panneauCentral.add(new JLabelUI("code postal:"),4,0);
			panneauCentral.add(getCodePostal (),4,1);
			
			panneauCentral.add(new JLabelUI("ville:"),4,2);
			panneauCentral.add(getVille (),4,3);

			
			panneauCentral.add(new JLabelUI("pays:"),5,0);
			panneauCentral.add(getPays (),5,1);
			
			
			panneauCentral.add(getPanneauControl (),6,0);
			
			
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
			
			tfLibelle.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_LIGNE_ADRESSE_RNVP, ToolRegex.TAILLE_LIGNE_ADRESSE_RNVP_MAXIMUM,DocumentFilterTextRegex. MODE_TRANSFORMATION.PREMIER_CARACTERE_MAJUSCULE));

		}
		
		return tfLibelle;
	}

	protected JTextField getLigne1 () {
		if (tfLigne1==null) {
			tfLigne1=new JTextFieldUI ();
			tfLigne1.setSize(UIManager.getDimension("TextField.dimension.large"));
			
			tfLigne1.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_LIGNE_ADRESSE_RNVP, ToolRegex.TAILLE_LIGNE_ADRESSE_RNVP_MAXIMUM,DocumentFilterTextRegex. MODE_TRANSFORMATION.PREMIER_CARACTERE_MAJUSCULE));


		}
		
		return tfLigne1;
	}

	protected JTextField getLigne2 () {
		if (tfLigne2==null) {
			tfLigne2=new JTextFieldUI ();
			tfLigne2.setSize(UIManager.getDimension("TextField.dimension.large"));
			
			tfLigne2.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_LIGNE_ADRESSE_RNVP, ToolRegex.TAILLE_LIGNE_ADRESSE_RNVP_MAXIMUM,DocumentFilterTextRegex. MODE_TRANSFORMATION.PREMIER_CARACTERE_MAJUSCULE));


		}
		
		return tfLigne2;
	} 

	protected JTextField getLigne3 () {
		if (tfLigne3==null) {
			tfLigne3=new JTextFieldUI ();
			tfLigne3.setSize(UIManager.getDimension("TextField.dimension.large"));
			
			tfLigne3.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_LIGNE_ADRESSE_RNVP, ToolRegex.TAILLE_LIGNE_ADRESSE_RNVP_MAXIMUM,DocumentFilterTextRegex. MODE_TRANSFORMATION.PREMIER_CARACTERE_MAJUSCULE));


		}
		
		return tfLigne3;
	}
	
	private JComboBox getCodePostal () {
		if (cbCodePostal==null) {
			cbCodePostal=new JComboBoxUI (codePostalComboBoxModel);
			
			cbCodePostal.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					codePostalChange ((String)((JComboBox)e.getSource()).getSelectedItem());
				}
			});
		}
		
		return cbCodePostal;
	}
    
	private JComboBox getVille () {
		if (cbVille==null) {
			cbVille=new JComboBoxUI (villeComboBoxModel);
			
			//affichage de la ville
			cbVille.setRenderer(new DefaultListCellRenderer() {
	            @Override
	            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	                //if(value instanceof CodePostal){
	                    setText(((CodePostal) value).getVille());
	                //}
	                return this;
	            }
	        } );
			
			
			cbVille.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					villeChange ((CodePostal)((JComboBox)e.getSource()).getSelectedItem());
				}
			});

		}
		
		return cbVille;
	}
    

	protected JTextField getPays () {
		if (tfPays==null) {
			tfPays=new JTextFieldUI ();
			tfPays.setEditable(false);
		}
		
		return tfPays;
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
			initUI ("Cr\u00E9ation d'une adresse");
		}else if (getMode ().equals(MODE_MODIFICATION)){
			initUI ("Modification d'une adresse");
		}else {
			throw new RuntimeException ("mode inconnu: "+getMode ());
		}
		
		initialiserModel ();
	}

	public void initialiserModel () {

//		codePostalComboBoxModel.setModel(codePostalService.getListeCodePostaux());
		villeComboBoxModel.initialiserModel ((String) codePostalComboBoxModel.getElementAt(0));
	}
	

	private void codePostalChange (String codePostal) {
		villeComboBoxModel.initialiserModel (codePostal);
	}
	
	private void villeChange (CodePostal codePostal) {
		if (codePostal!=null) {
			setSelectedCodePostal (codePostal);
		}
	}
	

	public void setSelectedCodePostal (CodePostal codePostal) {
		getCodePostal().setSelectedItem(codePostal);
		getVille ().setSelectedItem(codePostal);
		getPays ().setText(codePostal.getPays().getPays());
	}


	protected Adresse getSaisie () {
		Adresse adresse=new Adresse ();
		
		if (getMode ().equals(MODE_MODIFICATION)) {
			adresse.setId(getAdresse () .getId());
		}
		
		adresse.setLibelle(getLibelle ().getText());
		adresse.setLigne1(getLigne1().getText());
		adresse.setLigne2(getLigne2().getText());
		adresse.setLigne3(getLigne3().getText());
		adresse.setCodePostal((CodePostal)getVille().getSelectedItem());
		
		return adresse;
	}
	
	private void enregistrer () {

		Adresse adresse;
		AdresseCreateRequest request;
		
			try {
				Adresse adr= getSaisie ();
				
				adresseService.validerAdresse(adr);
				
				request=new AdresseCreateRequest ();
				
				request.setId(adr.getId());
				request.setLibelle(adr.getLibelle());
				request.setLigne1(adr.getLigne1());
				request.setLigne2(adr.getLigne2());
				request.setLigne3(adr.getLigne3());
				request.setCodePostal(adr.getCodePostal());
				
				adresse=adresseService.createAdresse(request);
				
				//adresseTableModel.addData(adresse);
				if (getMode ().equals(MODE_MODIFICATION)) {
					adresseTableModel.replaceData (adresse);
				}
				else {
					adresseTableModel.addData(adresse);
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
	



}
