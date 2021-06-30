package com.planificateurVoyage.ui.metier.referentiel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.RowSorterEvent;

import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.request.CategorieCreateRequest;
import com.planificateurVoyage.service.CategorieService;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.ui.model.CategorieComboBoxModel;
import com.planificateurVoyage.ui.model.CategorieTableModel;
import com.planificateurVoyage.ui.model.PaysComboBoxModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.DocumentFilterTextRegex;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JComboBoxUI;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelFormulaire;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTableUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReferentielCategorieAjoutPanel extends AbstractPanelTitle {
	
	private final CategorieService categorieService;
	
	private final CategorieTableModel categorieTableModel;
	private JTableUI tableCategorie;
	
    private JTextFieldUI tfLibelle;
	
	//UI
	private JPanelFormulaire panneauSaisie;
	private JPanel panneauCentral;
	private JPanelUI panneauControl;
	

	private final CategorieComboBoxModel categorieComboBoxModel;

	
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			panneauCentral=new JPanelUI (new BorderLayout ());

			panneauCentral.add(new JScrollPaneUI(getTableCategorie ()),BorderLayout.NORTH);
			panneauCentral.add(getPanneauSaisie (),BorderLayout.CENTER);

		}
		
		return panneauCentral;
	}
	
	protected JPanel getPanneauSaisie () {
		if (panneauSaisie==null) {
			
			panneauSaisie=new JPanelFormulaire ();
			
			panneauSaisie.add(new JLabelUI("Nouvelle Cat\u00E9gorie", (Font)UIManager.get ("Label.font.titre.1"),(Dimension) UIManager.get ("Label.dimension.large")),0,1);

			panneauSaisie.add(new JLabelUI("libelle"),1,0);
			panneauSaisie.add(getLibelle (),1,1);
			
			panneauSaisie.add(getPanneauControl (),2,0);
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
	
	protected JTableUI getTableCategorie () {
		if (tableCategorie==null) {
			tableCategorie=new JTableUI (categorieTableModel);
		}
		
		return tableCategorie;
	}
	
	protected JTextField getLibelle () {
		if (tfLibelle==null) {
			tfLibelle=new JTextFieldUI ();
			
			tfLibelle.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_ALPHANUMERIQUE,DocumentFilterTextRegex.MODE_TRANSFORMATION.PREMIER_CARACTERE_MAJUSCULE));

		}
		
		return tfLibelle;
	}
	
	
	protected void init () {
		
		initUI ("Ajout d'une cat\u00E9gorie");
		
		initialiserModel ();
	}
	
	public void initialiserModel () {
		setModel (categorieService.readCategories());
	}
	

	
	protected void setModel (List<Categorie> listCategorie) {

		tableCategorie.setRowSorter(null);
		
		categorieTableModel.setModel(listCategorie);
		
		tableCategorie.setAutoCreateRowSorter(true);
		tableCategorie.sorterChanged(new RowSorterEvent (tableCategorie.getRowSorter()));
	}
	
	private boolean isFormulaireValide () {
		return (!getLibelle ().getText().isEmpty());
	}
	
	private void enregistrer () {
		Categorie categorie;
		
		CategorieCreateRequest request;
		
		if (isFormulaireValide ()) {
			try {
				request=new CategorieCreateRequest ();
				
				request.setLibelle(getLibelle().getText());
				
				categorie=categorieService.createCategorie(request);
				
				categorieTableModel.addData(categorie);
				categorieComboBoxModel.addElement(categorie);
				
				JOptionPane.showMessageDialog(this,"donn\u00E9e enregistr\u00E9e.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this,"le formulaire est incomplet.","Warning",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
}