package com.planificateurVoyage.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;

import com.planificateurVoyage.ui.metier.activite.ActiviteConsultationPanel;
import com.planificateurVoyage.ui.metier.activite.ActiviteCreationPanel;
import com.planificateurVoyage.ui.metier.adresse.AdresseConsultationPanel;
import com.planificateurVoyage.ui.metier.adresse.AdresseCreationPanel;
import com.planificateurVoyage.ui.metier.personne.PersonneConsultationPanel;
import com.planificateurVoyage.ui.metier.personne.PersonneCreationPanel;
import com.planificateurVoyage.ui.metier.referentiel.ReferentielCategorieAjoutPanel;
import com.planificateurVoyage.ui.metier.referentiel.ReferentielCodePostalAjoutPanel;
import com.planificateurVoyage.ui.metier.referentiel.ReferentielPaysAjoutPanel;
import com.planificateurVoyage.ui.metier.voyage.VoyageConsultationPanel;
import com.planificateurVoyage.ui.metier.voyage.VoyageCreationPanel;
import com.planificateurVoyage.ui.tool.JButtonUI;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class MainFrame extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
    
	
	// CONSTANTES
	private static final String PERSONNE_CONSULTATION="PERSONNE_CONSULTATION";
	private static final String PERSONNE_CREATION="PERSONNE_CREATION";
	private static final String ACTIVITE_CONSULTATION="ACTIVITE_CONSULTATION";
	private static final String ACTIVITE_CREATION="ACTIVITE_CREATION";
	private static final String VOYAGE_CONSULTATION="VOYAGE_CONSULTATION";
	private static final String VOYAGE_CREATION="VOYAGE_CREATION";
//	private static final String ACTIVITE_CREATION_VOYAGE="ACTIVITE_CREATION_VOYAGE";
	private static final String ADRESSE_CONSULTATION="ADRESSE_CONSULTATION";
	private static final String ADRESSE_CREATION="ADRESSE_CREATION";
	private static final String REFERENTIEL_CATEGORIE_AJOUT="REFERENTIEL_CATEGORIE_AJOUT";
	private static final String REFERENTIEL_CODE_POSTAL_AJOUT="REFERENTIEL_CODE_POSTAL_AJOUT";
	private static final String REFERENTIEL_PAYS_AJOUT="REFERENTIEL_PAYS_AJOUT";
	
	
	// CHAMPS
//	@Autowired
	private final PersonneConsultationPanel personneConsultationPanel;
	private final PersonneCreationPanel personneCreationPanel;
	private final ActiviteConsultationPanel activiteConsultationPanel;
	private final ActiviteCreationPanel activiteCreationPanel;
	private final VoyageConsultationPanel voyageConsultationPanel;
	private final VoyageCreationPanel voyageCreationPanel;
//	private final ActiviteCreationVoyagePanel activiteCreationVoyage=new ActiviteCreationVoyagePanel ();
	private final AdresseConsultationPanel adresseConsultationPanel;
	private final AdresseCreationPanel adresseCreationPanel;
	private final ReferentielCategorieAjoutPanel referentielCategorieAjoutPanel;
	private final ReferentielCodePostalAjoutPanel referentielCodePostalAjoutPanel;
	private final ReferentielPaysAjoutPanel referentielPaysAjoutPanel;
	
	
	private final JPanel panneauPrincipal=new JPanel();

	
	// GETTERS SETTERS
	public PersonneConsultationPanel getPersonneConsultation() {
		return personneConsultationPanel;
	}
	public PersonneCreationPanel getPersonneCreation() {
		return personneCreationPanel;
	}
	public ActiviteConsultationPanel getActiviteConsultation() {
		return activiteConsultationPanel;
	}
	public ActiviteCreationPanel getActiviteCreation() {
		return activiteCreationPanel;
	}
	public VoyageConsultationPanel getVoyageConsultation() {
		return voyageConsultationPanel;
	}
	public VoyageCreationPanel getVoyageCreation() {
		return voyageCreationPanel;
	}
//	public ActiviteCreationVoyagePanel getActiviteCreationVoyage() {
//		return activiteCreationVoyage;
//	}
	
	private AdresseConsultationPanel getAdresseConsultationPanel () {
		return adresseConsultationPanel;
	}
	
	private AdresseCreationPanel getAdresseCreationPanel () {
		return adresseCreationPanel;
	}
	
	private ReferentielCategorieAjoutPanel getReferentielCategorieAjoutPanel () {
		return referentielCategorieAjoutPanel;
	}
	
	private ReferentielCodePostalAjoutPanel getReferentielCodePostalAjoutPanel () {
		return referentielCodePostalAjoutPanel;
	}

	private ReferentielPaysAjoutPanel getReferentielPaysAjoutPanel () {
		return referentielPaysAjoutPanel;
	}

	private JPanel getPanneauPrincipal () {
		return panneauPrincipal;
	}
	
	//CONSTRUCTEUR
	public void init() {
		   
	    setState(Frame.NORMAL);
		
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    setSize(new Dimension(1195, 780));	// 1200x800
		

	    setTitle ("Panificateur de Voyage");
	    
	    try {
			this.setIconImage(new ImageIcon(new ClassPathResource("icone/voyage.png").getURL()).getImage());
		} catch (IOException e) {
			logger.error("erreur sur l'icon de la fenetre",e);
		}

		// initialisation des composants graphiques
		//initMenu ();

		intToolBar ();

	    initPanneauPrincipal ();
	       
		getContentPane().add(getPanneauPrincipal (), BorderLayout.CENTER);
	    
voirPanneauPrincipal (VOYAGE_CONSULTATION);
//		voirPanneauPrincipal (ACTIVITE_CONSULTATION);
	    
	    setVisible(true);
	       
		logger.info("Fenêtre principale initialisée.");
	}

	
	
	private void initPanneauPrincipal (){
		panneauPrincipal.setLayout(new CardLayout());
		
		panneauPrincipal.add(getPersonneConsultation(), PERSONNE_CONSULTATION);
		panneauPrincipal.add(getPersonneCreation(), PERSONNE_CREATION);
		panneauPrincipal.add(getActiviteConsultation(), ACTIVITE_CONSULTATION);
		panneauPrincipal.add(getActiviteCreation(), ACTIVITE_CREATION);	
		panneauPrincipal.add(getVoyageConsultation(), VOYAGE_CONSULTATION);	
		panneauPrincipal.add(getVoyageCreation(), VOYAGE_CREATION);	
//		panneauPrincipal.add(getActiviteCreationVoyage(), ACTIVITE_CREATION_VOYAGE);	
		panneauPrincipal.add(getAdresseConsultationPanel(), ADRESSE_CONSULTATION);	
		panneauPrincipal.add(getAdresseCreationPanel(), ADRESSE_CREATION);	
		panneauPrincipal.add(getReferentielCategorieAjoutPanel(), REFERENTIEL_CATEGORIE_AJOUT);	
		panneauPrincipal.add(getReferentielCodePostalAjoutPanel(), REFERENTIEL_CODE_POSTAL_AJOUT);	
		panneauPrincipal.add(getReferentielPaysAjoutPanel(), REFERENTIEL_PAYS_AJOUT);	

	
	}
	
	private void intToolBar () {
		try {
			JToolBar toolBar = new JToolBar("Still draggable");
			getContentPane().add(toolBar, BorderLayout.NORTH);

			
			//voyage
			JButtonUI buttonVoyageConsultation = new JButtonUI(new ImageIcon(new ClassPathResource("icone/voyage.png").getURL()));
			buttonVoyageConsultation.setToolTipText("Consultation voyage");
			buttonVoyageConsultation.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (VOYAGE_CONSULTATION);
				}
			});
			toolBar.add(buttonVoyageConsultation);
			
			
			JButtonUI buttonVoyageCreation = new JButtonUI(new ImageIcon(new ClassPathResource("icone/add voyage.png").getURL()));
			buttonVoyageCreation.setToolTipText("Cr\u00E9ation voyage");
			buttonVoyageCreation.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (VOYAGE_CREATION);
				}
			});
			toolBar.add(buttonVoyageCreation);
			
			toolBar.addSeparator();
	
			
			//personne
			JButtonUI buttonPersonneConsultation = new JButtonUI(new ImageIcon(new ClassPathResource("icone/personne.png").getURL()));
			buttonPersonneConsultation.setToolTipText("Consultation personne");
			buttonPersonneConsultation.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (PERSONNE_CONSULTATION);
				}
			});
			toolBar.add(buttonPersonneConsultation);
			
			
			JButtonUI buttonPersonneCreation = new JButtonUI(new ImageIcon(new ClassPathResource("icone/add personne.png").getURL()));
			buttonPersonneCreation.setToolTipText("Cr\u00E9ation personne");
			buttonPersonneCreation.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (PERSONNE_CREATION);
				}
			});
			toolBar.add(buttonPersonneCreation);
			
			toolBar.addSeparator();
		
			
			//Activite
			JButtonUI buttonActiviteConsultation = new JButtonUI(new ImageIcon(new ClassPathResource("icone/activite.png").getURL()));
			buttonActiviteConsultation.setToolTipText("Consultation activit\u00E9");
			buttonActiviteConsultation.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (ACTIVITE_CONSULTATION);
				}
			});
			toolBar.add(buttonActiviteConsultation);
			
			JButtonUI buttonActiviteCreation = new JButtonUI(new ImageIcon(new ClassPathResource("icone/add activite.png").getURL()));
			buttonActiviteCreation.setToolTipText("Cr\u00E9ation activit\u00E9");
			buttonActiviteCreation.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (ACTIVITE_CREATION);
				}
			});
			toolBar.add(buttonActiviteCreation);
			
			toolBar.addSeparator();


			//Adresse
			JButtonUI buttonAdresseConsultation = new JButtonUI(new ImageIcon(new ClassPathResource("icone/adresse.png").getURL()));
			buttonAdresseConsultation.setToolTipText("Consultation adresse");
			buttonAdresseConsultation.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (ADRESSE_CONSULTATION);
				}
			});
			toolBar.add(buttonAdresseConsultation);
			
			
			JButtonUI buttonAdresseCreation = new JButtonUI(new ImageIcon(new ClassPathResource("icone/add adresse.png").getURL()));
			buttonAdresseCreation.setToolTipText("Cr\u00E9ation adresse");
			buttonAdresseCreation.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (ADRESSE_CREATION);
				}
			});
			toolBar.add(buttonAdresseCreation);
			
			toolBar.addSeparator();

			
			//Referentiel Categorie
			JButtonUI buttonCategorieAjout = new JButtonUI(new ImageIcon(new ClassPathResource("icone/categorie.png").getURL()));
			buttonCategorieAjout.setToolTipText("ajout cat\u00E9gorie");
			buttonCategorieAjout.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (REFERENTIEL_CATEGORIE_AJOUT);
				}
			});
			toolBar.add(buttonCategorieAjout);
			
			
			//Referentiel CodePostal
			JButtonUI buttonCodePostalAjout = new JButtonUI(new ImageIcon(new ClassPathResource("icone/code postal.png").getURL()));
			buttonCodePostalAjout.setToolTipText("ajout code postal");
			buttonCodePostalAjout.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (REFERENTIEL_CODE_POSTAL_AJOUT);
				}
			});
			toolBar.add(buttonCodePostalAjout);
			
			
			//Referentiel Pays
			JButtonUI buttonPaysAjout = new JButtonUI(new ImageIcon(new ClassPathResource("icone/pays.png").getURL()));
			buttonPaysAjout.setToolTipText("ajout pays");
			buttonPaysAjout.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					voirPanneauPrincipal (REFERENTIEL_PAYS_AJOUT);
				}
			});
			toolBar.add(buttonPaysAjout);
			
	
			
		} catch (IOException e) {
			logger.error("erreur sur l'initialisation de la toolBar",e);
		}		
	}
	
	   private void initMenu () {
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);


			//Personne
			JMenu menuPersonne = new JMenu("Personne");
			menuBar.add(menuPersonne);
			
			JMenuItem menuPersonneConsultation = new JMenuItem("Consultation");
			menuPersonneConsultation.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Personne -> consultation");

					voirPanneauPrincipal (PERSONNE_CONSULTATION);
					
				}
			});
			menuPersonne.add(menuPersonneConsultation);
			
			JMenuItem menuPersonneCreation = new JMenuItem("Cr\u00E9ation");
			menuPersonneCreation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Personne -> création");
					
					voirPanneauPrincipal (PERSONNE_CREATION);

				}
			});
			menuPersonne.add(menuPersonneCreation);
			
			//Activité
			JMenu menuActivite = new JMenu("Activit\u00E9");
			menuBar.add(menuActivite);
			
			JMenuItem menuActiviteConsultation = new JMenuItem("Consultation");
			menuActiviteConsultation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Activité -> consultation");
					
					voirPanneauPrincipal (ACTIVITE_CONSULTATION);

				}
			});
			menuActivite.add(menuActiviteConsultation);
			
			JMenuItem menuActiviteCreation = new JMenuItem("Cr\u00E9ation");
			menuActiviteCreation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Activité -> création");
					
					voirPanneauPrincipal (ACTIVITE_CREATION);

				}
			});
			menuActivite.add(menuActiviteCreation);
			
			JMenu menuVoyage = new JMenu("Voyage");
			menuBar.add(menuVoyage);
			
			JMenuItem menuVoyageConsultation = new JMenuItem("Consultation");
			menuVoyageConsultation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Voyage -> consultation");
					
					voirPanneauPrincipal (VOYAGE_CONSULTATION);

				}
			});
			menuVoyage.add(menuVoyageConsultation);
			
			JMenuItem menuVoyageCreation = new JMenuItem("Cr\u00E9ation");
			menuVoyageCreation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Voyage -> création");
					
					voirPanneauPrincipal (VOYAGE_CREATION);

				}
			});
			menuVoyage.add(menuVoyageCreation);
			
			JMenu menuAdresse = new JMenu("Adresse");
			menuBar.add(menuAdresse);
			
			JMenuItem menuAdresseConsultation = new JMenuItem("Consultation");
			menuAdresseConsultation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Adresse -> consultation");
					
					voirPanneauPrincipal (ADRESSE_CONSULTATION);

				}
			});
			menuAdresse.add(menuAdresseConsultation);
			
			JMenuItem menuAdresseCreation = new JMenuItem("Cr\u00E9ation");
			menuAdresseCreation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Adresse -> création");
					
					voirPanneauPrincipal (ADRESSE_CREATION);

				}
			});
			menuAdresse.add(menuAdresseCreation);
			
			JMenu menuReferentiel = new JMenu("R\u00E9f\u00E9rentiel");
			menuBar.add(menuReferentiel);
			
			JMenuItem menuReferentielCategorieAjout = new JMenuItem("Cat\u00E9gorie");
			menuReferentielCategorieAjout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Categorie -> ajout");
					
					voirPanneauPrincipal (REFERENTIEL_CATEGORIE_AJOUT);

				}
			});
			menuReferentiel.add(menuReferentielCategorieAjout);
			
			JMenuItem menuReferentielCodePostalAjout = new JMenuItem("Code Postal");
			menuReferentielCodePostalAjout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Code Postal -> ajout");
					
					voirPanneauPrincipal (REFERENTIEL_CODE_POSTAL_AJOUT);

				}
			});
			menuReferentiel.add(menuReferentielCodePostalAjout);

			JMenuItem menuReferentielPaysAjout = new JMenuItem("Pays");
			menuReferentielPaysAjout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Pays -> ajout");
					
					voirPanneauPrincipal (REFERENTIEL_PAYS_AJOUT);

				}
			});
			menuReferentiel.add(menuReferentielPaysAjout);
	   }
	   
	   
	   //METHODE	
		private void voirPanneauPrincipal (String id)
		{
			((CardLayout)(getPanneauPrincipal ().getLayout())).show(getPanneauPrincipal (), id);
		}
	   

}
