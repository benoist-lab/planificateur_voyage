package com.planificateurVoyage.ui.metier.referentiel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.RowSorterEvent;

import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.request.PaysCreateRequest;
import com.planificateurVoyage.service.PaysService;
import com.planificateurVoyage.tools.ToolRegex;
import com.planificateurVoyage.ui.configuration.UIConfiguration;
import com.planificateurVoyage.ui.model.PaysComboBoxModel;
import com.planificateurVoyage.ui.model.PaysTableModel;
import com.planificateurVoyage.ui.tool.AbstractPanelTitle;
import com.planificateurVoyage.ui.tool.DocumentFilterTextRegex;
import com.planificateurVoyage.ui.tool.JButtonUI;
import com.planificateurVoyage.ui.tool.JLabelUI;
import com.planificateurVoyage.ui.tool.JPanelFormulaire;
import com.planificateurVoyage.ui.tool.JPanelUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTableUI;
import com.planificateurVoyage.ui.tool.JTextFieldUI;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReferentielPaysAjoutPanel extends AbstractPanelTitle {
	
	
	//Service
	private final PaysService paysService;

	
	//UI
	private JPanelFormulaire panneauSaisie;
	private JPanel panneauCentral;
	private JPanelUI panneauControl;
	
	private final PaysComboBoxModel paysComboBoxModel;

	
	private final PaysTableModel paysTableModel;
	private JTableUI tablePays;
	
    private JTextFieldUI tfPays;
	
	
	@Override
	protected JPanel getPanneauCentral () {
		if (panneauCentral==null) {
			panneauCentral=new JPanelUI (new BorderLayout ());

			panneauCentral.add(new JScrollPaneUI(getTablePays ()),BorderLayout.NORTH);
			panneauCentral.add(getPanneauSaisie (),BorderLayout.CENTER);
		}
		return panneauCentral;
	}
	
	protected JPanel getPanneauSaisie () {
		if (panneauSaisie==null) {
			
			panneauSaisie=new JPanelFormulaire ();
			
			panneauSaisie.add(new JLabelUI("Nouveau Pays", (Font)UIManager.get ("Label.font.titre.1")),0,1);
			panneauSaisie.add(new JLabelUI("pays"),1,0);
			panneauSaisie.add(getPays (),1,1);
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
	
	protected JTableUI getTablePays () {
		if (tablePays==null) {
			tablePays=new JTableUI (paysTableModel);
			
		}
		
		return tablePays;
	}
	
	protected JTextField getPays () {
		if (tfPays==null) {
			tfPays=new JTextFieldUI ();
			
			tfPays.setDocumentFilter(new DocumentFilterTextRegex(ToolRegex.REGEX_PAYS,DocumentFilterTextRegex.MODE_TRANSFORMATION.TOUT_CARACTERE_MAJUSCULE));

		}
		
		return tfPays;
	}
	
	protected void init () {
		
		initUI ("Ajout d'un pays");
		
		initialiserModel ();
		
	}
	
	public void initialiserModel () {
		setModel (paysService.readPays());
	}
	
	protected void setModel (List<Pays> listPays) {

		tablePays.setRowSorter(null);
		
		paysTableModel.setModel(listPays);
		
		tablePays.setAutoCreateRowSorter(true);

		tablePays.sorterChanged(new RowSorterEvent (tablePays.getRowSorter()));
	}
	
	
	protected Pays getSaisie () {
		Pays pays=new Pays ();
		
		pays.setPays(getPays ().getText());
		
		return pays;
	}
	
	private void enregistrer () {
		Pays pays;
		
		PaysCreateRequest request;

			try {
				paysService.validerPays(getSaisie ());
				
				request=new PaysCreateRequest ();
				
				request.setPays(getPays ().getText());
				
				pays=paysService.createPays(request);
				
				paysTableModel.addData(pays);
				paysComboBoxModel.addElement(pays);
				
				JOptionPane.showMessageDialog(this,"donn\u00E9e enregistr\u00E9e.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}

	}
	

}