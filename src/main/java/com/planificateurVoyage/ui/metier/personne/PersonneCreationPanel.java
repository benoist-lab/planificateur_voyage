package com.planificateurVoyage.ui.metier.personne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.request.PersonneCreateRequest;
import com.planificateurVoyage.service.PersonneService;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.ui.model.PersonneTableModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.DocumentFilterTextRegex;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelFormulaire;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonneCreationPanel extends AbstractPanelTitle {
	
	public static final String MODE_CREATION="MODE_CREATION";
	public static final String MODE_MODIFICATION="MODE_MODIFICATION";

	
	//Service
	protected final PersonneService personneService;

	protected final PersonneTableModel personneTableModel;
	
	private JPanelFormulaire panneauCentral;
	private JPanelUI panneauControl;
    
    private JTextFieldUI tfNom;
    private JTextFieldUI tfPrenom;
	
	private JDialog dialogMere=null;
	
	private String mode=MODE_CREATION;
	private Personne personneData=null;

	
	public void setMode (String mode) {
		this.mode=mode;
	}
	
	public String getMode () {
		return mode;
	}
	
	public void setPersonne (Personne personne) {
		
		personneData=personne;
		
		setTitre("Modification de  ("+personne.getId()+") "+personne.getNom()+" "+personne.getPrenom());
		getNom ().setText(personne.getNom());
		getPrenom ().setText(personne.getPrenom());
		
	}
	
	public Personne getPersonne () {
		return personneData;
	}

	
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			
			panneauCentral=new JPanelFormulaire ();

			panneauCentral.add(new JLabelUI("Nom:"),0,0);
			panneauCentral.add(getNom (),0,1);

			panneauCentral.add(new JLabelUI("Pr\u00E9nom:"),1,0);
			panneauCentral.add(getPrenom (),1,1);

			panneauCentral.add(getPanneauControl (),2,0);
			
			
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
				bAnnuler.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						annuler ();
					}				
				});
				panneauControl.add(bAnnuler);
			}			
		}
		
		return panneauControl;
	}
	
	protected JTextField getNom () {
		if (tfNom==null) {
			tfNom=new JTextFieldUI ();
			
			tfNom.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_NOM,DocumentFilterTextRegex.MODE_TRANSFORMATION.PREMIER_CARACTERE_MAJUSCULE));

		}
		
		return tfNom;
	}
	
	protected JTextField getPrenom () {
		if (tfPrenom==null) {
			tfPrenom=new JTextFieldUI ();
			
			tfPrenom.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_PRENOM,DocumentFilterTextRegex.MODE_TRANSFORMATION.PREMIER_CARACTERE_MAJUSCULE));

		}
		
		return tfPrenom;
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
			initUI ("Cr\u00E9ation d'une personne");
		}else if (getMode ().equals(MODE_MODIFICATION)){
			initUI ("Modification d'une personne");
		}else {
			throw new RuntimeException ("mode inconnu: "+getMode ());
		}

	}
	
	protected Personne getSaisie () {
		Personne personne=new Personne ();
		
		if (getMode ().equals(MODE_MODIFICATION)) {
			personne.setId(getPersonne () .getId());
		}
		
		personne.setNom(getNom ().getText());
		personne.setPrenom(getPrenom().getText());
		
		return personne;
	}
	
	private void enregistrer () {
		
		Personne personne;
		PersonneCreateRequest request;
		
			try {
				Personne pers=getSaisie ();
				
				personneService.validerPersonne(pers);
				
				request=new PersonneCreateRequest ();

				request.setId(pers.getId());

				request.setNom(pers.getNom());
				request.setPrenom(pers.getPrenom());
				
				personne=personneService.createPersonne(request);
				
				if (getMode ().equals(MODE_MODIFICATION)) {
					personneTableModel.replaceData (personne);
				}
				else {
					personneTableModel.addData(personne);
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
