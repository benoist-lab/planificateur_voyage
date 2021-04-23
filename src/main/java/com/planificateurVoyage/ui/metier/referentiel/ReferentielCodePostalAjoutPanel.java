package com.planificateurVoyage.ui.metier.referentiel;

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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.RowSorterEvent;
import javax.swing.text.AbstractDocument;

import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.request.CodePostalCreateRequest;
import com.planificateurVoyage.model.request.PaysCreateRequest;
import com.planificateurVoyage.service.CodePostalService;
import com.planificateurVoyage.service.PaysService;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.ui.configuration.UIConfiguration;
import com.planificateurVoyage.ui.model.CodePostalComboBoxModel;
import com.planificateurVoyage.ui.model.CodePostalTableModel;
import com.planificateurVoyage.ui.model.PaysComboBoxModel;
import com.planificateurVoyage.ui.model.PaysTableModel;
import com.planificateurVoyage.ui.model.VilleComboBoxModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JComboBoxUI;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelFormulaire;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTableUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;
import com.planificateurVoyage.ui.tool.DocumentFilterTextLimiter;
import com.planificateurVoyage.ui.tool.DocumentFilterTextRegex;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReferentielCodePostalAjoutPanel extends AbstractPanelTitle {
	
	private final PaysService paysService;
	private final CodePostalService codePostalService;
	
	private final CodePostalComboBoxModel codePostalComboBoxModel;
	private final VilleComboBoxModel villeComboBoxModel;
	
	private final CodePostalTableModel codePostalTableModel;
	private JTableUI tableCodePostal;
	
	
    private JTextFieldUI tfCodePostal;
    private JTextFieldUI tfVille;

	
	private final PaysComboBoxModel paysComboBoxModel;
	private JComboBox cbPays;
	
	//UI
	private JPanelFormulaire panneauSaisie;
	private JPanel panneauCentral;
	private JPanelUI panneauControl;
	
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			panneauCentral=new JPanelUI (new BorderLayout ());

			panneauCentral.add(new JScrollPaneUI(getTableCodePostal ()),BorderLayout.NORTH);
			panneauCentral.add(getPanneauSaisie (),BorderLayout.CENTER);
		}
		return panneauCentral;
	}
	
	protected JPanel getPanneauSaisie () {
		if (panneauSaisie==null) {
			
			panneauSaisie=new JPanelFormulaire ();
			
			panneauSaisie.add(new JLabelUI("Nouveau Code Postal", (Font)UIManager.get ("Label.font.titre.1"),(Dimension) UIManager.get ("Label.dimension.large")),0,1);
			panneauSaisie.add(new JLabelUI("code postal"),1,0);
			panneauSaisie.add(getCodePostal (),1,1);
			
			panneauSaisie.add(new JLabelUI("ville"),2,0);
			panneauSaisie.add(getVille (),2,1);
			
			panneauSaisie.add(new JLabelUI("pays"),3,0);
			panneauSaisie.add(getPays (),3,1);
			
			panneauSaisie.add(getPanneauControl (),4,0);
		}
		
		return panneauSaisie;
	}
	
	
	public JPanel getPanneauControl () {
		if (panneauControl==null) {
			panneauControl=new JPanelUI (new FlowLayout (FlowLayout.RIGHT));
			
			panneauControl.setSize (new Dimension (((JPanelFormulaire)getPanneauSaisie ()).getLargeurTotal(),((Dimension) UIManager.get("Button.dimension")).height+10));
			
			JButtonUI bOk=new JButtonUI ("Ok");
			bOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					enregistrer ();
				}				
			});
			panneauControl.add(bOk);
	
		}
		
		return panneauControl;
	}
	
	protected JTableUI getTableCodePostal () {
		if (tableCodePostal==null) {
			tableCodePostal=new JTableUI (codePostalTableModel);
		}
		
		return tableCodePostal;
	}
	
	
	
	protected JTextField getCodePostal () {
		if (tfCodePostal==null) {
			tfCodePostal=new JTextFieldUI ();

			tfCodePostal.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_CODE_POSTAL,ToolRegex.TAILLE_CODE_POSTAL_MAXIMUM,DocumentFilterTextRegex.MODE_TRANSFORMATION.TOUT_CARACTERE_MAJUSCULE));

		}
		
		return tfCodePostal;
	}
	
	protected JTextField getVille () {
		if (tfVille==null) {
			tfVille=new JTextFieldUI ();
			
			tfVille.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_VILLE,DocumentFilterTextRegex.MODE_TRANSFORMATION.PREMIER_CARACTERE_MAJUSCULE));
			
		}
		
		return tfVille;
	}	
	
	
	private JComboBox getPays () {
		if (cbPays==null) {
			cbPays=new JComboBoxUI (paysComboBoxModel);
		}
		
		return cbPays;
	}
	
	protected void init () {
		
		initUI ("Ajout d'un code postal");
		
		initialiserModel ();
		
	}
	

	public void initialiserModel () {
		setModel (codePostalService.readCodePostaux());
		paysComboBoxModel.setModel(paysService.readPays());
	}
	
	
	protected void setModel (List<CodePostal> listCodePostal) {

		tableCodePostal.setRowSorter(null);
		
		codePostalTableModel.clearData();
		
		for (CodePostal cp:listCodePostal) {
			codePostalTableModel.addData(cp);
		}
		
		tableCodePostal.setAutoCreateRowSorter(true);


		tableCodePostal.sorterChanged(new RowSorterEvent (tableCodePostal.getRowSorter()));
	}
	

	protected CodePostal getSaisie () {
		CodePostal codePostal=new CodePostal ();
		
		codePostal.setCodePostal(getCodePostal().getText());
		codePostal.setVille(getVille ().getText());
		codePostal.setPays((Pays) getPays ().getSelectedItem());
		
		return codePostal;
	}

	
	private void enregistrer () {
		CodePostal codePostal;
		
		CodePostalCreateRequest request;
		
			try {
				codePostalService.validerCodePostal(getSaisie ());
				
				request=new CodePostalCreateRequest ();
				
				request.setCodePostal(getCodePostal().getText());
				request.setVille(getVille ().getText());
				request.setPays((Pays) getPays ().getSelectedItem());
				
				codePostal=codePostalService.createCodePostal(request);
				
				codePostalTableModel.addData(codePostal);
				codePostalComboBoxModel.initialiserModel ();
				villeComboBoxModel.reinitialiserModel();
				
				JOptionPane.showMessageDialog(this,"donn\u00E9e enregistr\u00E9e.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}

			
	}

}