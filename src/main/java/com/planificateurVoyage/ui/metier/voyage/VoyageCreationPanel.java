package com.planificateurVoyage.ui.metier.voyage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.StatutVoyage;
import com.planificateurVoyage.model.request.PersonneCreateRequest;
import com.planificateurVoyage.service.StatutVoyageService;
import com.planificateurVoyage.ui.model.CategorieComboBoxModel;
import com.planificateurVoyage.ui.model.StatutVoyageComboBoxModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JComboBoxUI;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelFormulaire;
import com.planificateurVoyage.ui.tool.JPanelUI;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VoyageCreationPanel extends AbstractPanelTitle {
	
	private final StatutVoyageService statutVoyageService;
	
	private final StatutVoyageComboBoxModel statutVoyageComboBoxModel;
	private JComboBox cbStatutVoyage;
	
	private JPanelFormulaire panneauCentral;
	private JPanelUI panneauControl;
	
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			
			panneauCentral=new JPanelFormulaire ();


			panneauCentral.add(new JLabelUI("statut:"),1,0);
			panneauCentral.add(getStatutVoyage (),1,1);

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
	
	private JComboBox getStatutVoyage () {
		if (cbStatutVoyage==null) {
			cbStatutVoyage=new JComboBoxUI (statutVoyageComboBoxModel);
		}
		
		return cbStatutVoyage;
	}
	
	
	
	protected void init () {

		initUI ("Cr\u00E9ation d'un voyage");
		
		initialiserModel ();
	}
	
	
	
	public void initialiserModel () {
		statutVoyageComboBoxModel.setModel (statutVoyageService.readStatutVoyages());
	}
	
//	private boolean isFormulaireValide () {
//		//////////////////////////////////////////////////////////////////
//		//return ((!getNom ().getText().isEmpty()) && (!getPrenom().getText().isEmpty()));
//		return false;
//	}
	
	private void enregistrer () {
		
		//////////////////////////////////////////////////////////////////

//		Personne personne;
//		PersonneCreateRequest request;
//		
//		if (isFormulaireValide ()) {
//			try {
//				request=new PersonneCreateRequest ();
//				
//				request.setNom(getNom ().getText());
//				request.setPrenom(getPrenom().getText());
//				
//				personne=personneService.createPersonne(request);
//				
//				personneTableModel.addData(personne);
//				
//				JOptionPane.showMessageDialog(this,"donn\u00E9e enregistr\u00E9e.","Message",JOptionPane.INFORMATION_MESSAGE);
//			}
//			catch (Exception e) {
//				JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//			}
//		} else {
//			JOptionPane.showMessageDialog(this,"le formulaire est incomplet.","Warning",JOptionPane.WARNING_MESSAGE);
//		}
		
		JOptionPane.showMessageDialog(this, "Enregistrer");
	}

	private void annuler () {
		
		//////////////////////////////////////////////////////////////////
		
		JOptionPane.showMessageDialog(this, "Annuler");
	}


}
