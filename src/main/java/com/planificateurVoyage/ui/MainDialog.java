package com.planificateurVoyage.ui;


import java.awt.CardLayout;
import java.awt.Dimension;


import javax.swing.JPanel;

import com.planificateurVoyage.ui.metier.activite.ActiviteCreationPanel;
import com.planificateurVoyage.ui.metier.adresse.AdresseCreationPanel;
import com.planificateurVoyage.ui.metier.personne.PersonneCreationPanel;
import com.planificateurVoyage.ui.metier.referentiel.ReferentielCodePostalAjoutPanel;
import com.planificateurVoyage.ui.metier.voyage.VoyageCreationPanel;
import com.planificateurVoyage.ui.tool.JDialogUI;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainDialog extends JDialogUI {
	
	public static final String PERSONNE_MODIFICATION="PERSONNE_MODIFICATION";
	public static final String ADRESSE_MODIFICATION="ADRESSE_MODIFICATION";
	public static final String ACTIVITE_MODIFICATION="ACTIVITE_MODIFICATION";
	public static final String REFERENTIEL_CODE_POSTAL_AJOUT="REFERENTIEL_CODE_POSTAL_AJOUT";
	public static final String VOYAGE_MODIFICATION="VOYAGE_MODIFICATION";
	
	private final PersonneCreationPanel personneModificationPanel;
	private final AdresseCreationPanel adresseModificationPanel;
	private final ActiviteCreationPanel activiteModificationPanel;
	private final ReferentielCodePostalAjoutPanel referentielCodePostalAjoutPanelDialog;
	private final VoyageCreationPanel voyageModificationPanel;

	private final JPanel panneauPrincipal=new JPanel();
	

	private JPanel getPanneauPrincipal () {
		return panneauPrincipal;
	}
	
	public PersonneCreationPanel getPersonneModificationPanel () {
		return personneModificationPanel;
	}
	
	public AdresseCreationPanel getAdresseModificationPanel () {
		return adresseModificationPanel;
	}
	
	public ActiviteCreationPanel getActiviteModificationPanel () {
		return activiteModificationPanel;
	}
	
	public ReferentielCodePostalAjoutPanel getReferentielCodePostalAjoutPanelDialog () {
		return referentielCodePostalAjoutPanelDialog;
	}
	
	public VoyageCreationPanel getVoyageModificationPanel () {
		return voyageModificationPanel;
	}
	
	public void init () {
		setLocation (50,50);
		setSize(new Dimension(1000, 700));
		setModal(true);
		
		initPanneauPrincipal ();
		getContentPane().add(getPanneauPrincipal ());
		
	}
	
	
	private void initPanneauPrincipal (){
		panneauPrincipal.setLayout(new CardLayout());
		
		panneauPrincipal.add(getReferentielCodePostalAjoutPanelDialog(), REFERENTIEL_CODE_POSTAL_AJOUT);
		
		panneauPrincipal.add(getPersonneModificationPanel (),PERSONNE_MODIFICATION);
		getPersonneModificationPanel ().setDialogMere(this);
		
		panneauPrincipal.add(getAdresseModificationPanel (),ADRESSE_MODIFICATION);
		getAdresseModificationPanel ().setDialogMere(this);
		
		panneauPrincipal.add(getActiviteModificationPanel (),ACTIVITE_MODIFICATION);
		getActiviteModificationPanel ().setDialogMere(this);
		
		
		panneauPrincipal.add(getVoyageModificationPanel (),VOYAGE_MODIFICATION);
		getVoyageModificationPanel ().setDialogMere(this);
		
	}
	
	
	
	public void voirPanneauPrincipal (String id)
	{
		
		switch(id) {
			case REFERENTIEL_CODE_POSTAL_AJOUT:
				setTitle("ajout code postal");
				break;
			case PERSONNE_MODIFICATION:
				setTitle("modification personne");
				break;
			case ADRESSE_MODIFICATION:
				setTitle("modification adresse");
			case ACTIVITE_MODIFICATION:
				setTitle("modification activit\u00E9");
				break;
			case VOYAGE_MODIFICATION:
				setTitle("modification voyage");
				break;
		
		}
		((CardLayout)(getPanneauPrincipal ().getLayout())).show(getPanneauPrincipal (), id);
		
		setVisible(true);
	}
	


}
