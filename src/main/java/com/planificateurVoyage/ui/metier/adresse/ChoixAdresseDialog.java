package com.planificateurVoyage.ui.metier.adresse;

import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JTable;

import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.service.AdresseService;
import com.planificateurVoyage.ui.model.AdresseTableModel;
import com.planificateurVoyage.ui.tool.JDialogUI;
import com.planificateurVoyage.ui.tool.JScrollPaneUI;
import com.planificateurVoyage.ui.tool.JTableUI;
import com.planificateurVoyage.ui.tool.adpater.DoubleClickTableMouseAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChoixAdresseDialog extends JDialogUI{
	
	
	private final  AdresseService adresseService;
	private final AdresseTableModel adresseTableModel;	
    private JTableUI tableAdresse;
    
    private Adresse adresseChoisie=null;
    
    
    public Adresse getAdresseChoisie () {
    	return adresseChoisie;
    }
    
    protected void setAdresseChoisie (Adresse adresse) {
    	adresseChoisie=adresse;
    }
    
    protected JTable getTableAdresse () {
		if (tableAdresse==null) {
			tableAdresse = new JTableUI(adresseTableModel);

			tableAdresse.addMouseListener(new DoubleClickTableMouseAdapter() {
				@Override
				public void onDoubleClick() {
					setAdresseChoisie (((AdresseTableModel)getTableAdresse ().getModel()).getData(getRowClick ()));
					setVisible (false);
				}
			});
		}
		return tableAdresse;
	}
	
	public void initialiserModel () {

		setTitle ("choisissez une adresse");
		
		setLocation (50,50);
		setSize (1000,500);
		setModal(true);
		
		addComponentListener(new ComponentAdapter () {
			@Override
			public void componentShown(ComponentEvent e) {
				adresseChoisie=null;
			}
		});
		
		getContentPane().add(new JScrollPaneUI(getTableAdresse ()));
		
		
	}
	
}
