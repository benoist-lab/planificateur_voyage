package com.planificateurVoyage.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.planificateurVoyage.sqlite.BaseInitialiser;
import com.planificateurVoyage.ui.MainDialog;
import com.planificateurVoyage.ui.MainFrame;
import com.planificateurVoyage.ui.configuration.UIConfiguration;
import com.planificateurVoyage.ui.metier.activite.ActiviteConsultationPanel;
import com.planificateurVoyage.ui.metier.activite.ActiviteCreationPanel;
import com.planificateurVoyage.ui.metier.adresse.AdresseConsultationPanel;
import com.planificateurVoyage.ui.metier.adresse.AdresseCreationPanel;
import com.planificateurVoyage.ui.metier.adresse.ChoixAdresseDialog;
import com.planificateurVoyage.ui.metier.personne.ChoixPersonneDialog;
import com.planificateurVoyage.ui.metier.personne.PersonneConsultationPanel;
import com.planificateurVoyage.ui.metier.personne.PersonneCreationPanel;
import com.planificateurVoyage.ui.metier.referentiel.ReferentielCategorieAjoutPanel;
import com.planificateurVoyage.ui.metier.referentiel.ReferentielCodePostalAjoutPanel;
import com.planificateurVoyage.ui.metier.referentiel.ReferentielPaysAjoutPanel;
import com.planificateurVoyage.ui.metier.voyage.VoyageConsultationPanel;
import com.planificateurVoyage.ui.metier.voyage.VoyageCreationPanel;
import com.planificateurVoyage.ui.model.ActiviteTableModel;
import com.planificateurVoyage.ui.model.AdresseTableModel;
import com.planificateurVoyage.ui.model.CategorieComboBoxModel;
import com.planificateurVoyage.ui.model.CategorieTableModel;
import com.planificateurVoyage.ui.model.CodePostalComboBoxModel;
import com.planificateurVoyage.ui.model.CodePostalTableModel;
import com.planificateurVoyage.ui.model.PaysComboBoxModel;
import com.planificateurVoyage.ui.model.PaysTableModel;
import com.planificateurVoyage.ui.model.PersonneTableModel;
import com.planificateurVoyage.ui.model.StatutVoyageComboBoxModel;
import com.planificateurVoyage.ui.model.VilleComboBoxModel;
import com.planificateurVoyage.ui.model.VoyageTableModel;
import com.planificateurVoyage.ui.tool.JDialogUI;


@Configuration
public class ApplicationConfiguration {

		//Base Données
	  @Bean
	  DefaultConfigurationBean configBeanBaseInitiliser () {
	      return new DefaultConfigurationBean (BaseInitialiser.class,"baseInitialiser","initialiserBase");
	  }

	  // l'ordre compte donc doit etre fait avant la creation des UI
	  @Bean
	  DefaultConfigurationBean configBeanUIConfiguration () {
	      return new DefaultConfigurationBean(UIConfiguration.class,"uIConfiguration","initialiser");
	  }
	  
	  //Fenetre
	  @Bean
	  DefaultConfigurationBean configBeanMainFrame () {
	      return new DefaultConfigurationBean (MainFrame.class,"mainFrame","init");
	  }

	  @Bean
	  DefaultConfigurationBean configBeanMainDialog () {
	      return new DefaultConfigurationBean (MainDialog.class,"mainDialog","init");
	  }
	  
	  
	  //Personne
	  @Bean
	  DefaultConfigurationBean configBeanPersonneConsultationPanel () {
	      return new DefaultConfigurationBean(PersonneConsultationPanel.class,"personneConsultationPanel","init");
	  }	 

	  @Bean
	  DefaultConfigurationBean configBeanPersonneCreationPanel () {
	      return new DefaultConfigurationBean(PersonneCreationPanel.class,"personneCreationPanel","init");
	  }

	  @Bean
	  DefaultConfigurationBean configBeanPersonneModificationPanel () {
		  Map<String,Object> properties;
		  
		  properties=new HashMap ();
		  properties.put("mode",PersonneCreationPanel.MODE_MODIFICATION);
		  
	      return new DefaultConfigurationBean(PersonneCreationPanel.class,"personneModificationPanel","init",properties);
	  }

	  
	  @Bean
	  DefaultConfigurationBean configBeanPersonneTableModel () {
	      return new DefaultConfigurationBean(PersonneTableModel.class,"personneTableModel","initialiserModel");
	  }
	  
	  
	  @Bean
	  DefaultConfigurationBean configBeanChoixPersonneDialog () {
	      return new DefaultConfigurationBean(ChoixPersonneDialog.class,"choixPersonneDialog","initialiserModel");
	  }

	  //Pays
	  @Bean
	  DefaultConfigurationBean configBeanReferentielPaysAjoutPanel () {
	      return new DefaultConfigurationBean(ReferentielPaysAjoutPanel.class,"referentielPaysAjoutPanel","init");
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanPaysTableModel () {
	      return new DefaultConfigurationBean(PaysTableModel.class,"paysTableModel","initialiserModel");
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanPaysComboBoxModel () {
	      return new DefaultConfigurationBean(PaysComboBoxModel.class,"paysComboBoxModel","initialiserModel");
	  }
	  
	  
	  // Code Postal
	  @Bean
	  DefaultConfigurationBean configBeanReferentielCodePostalAjoutPanel () {
	      return new DefaultConfigurationBean(ReferentielCodePostalAjoutPanel.class,"referentielCodePostalAjoutPanel","init");
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanReferentielCodePostalAjoutPanelDialog () {
	      return new DefaultConfigurationBean(ReferentielCodePostalAjoutPanel.class,"referentielCodePostalAjoutPanelDialog","init");
	  }

	  @Bean
	  DefaultConfigurationBean configBeanCodePostalTableModel () {
	      return new DefaultConfigurationBean(CodePostalTableModel.class,"codePostalTableModel","initialiserModel");
	  }
	  
	  
	  @Bean
	  DefaultConfigurationBean configBeanCodePostalComboBoxModel () {
	      return new DefaultConfigurationBean(CodePostalComboBoxModel.class,"codePostalComboBoxModel","initialiserModel");
	  }
	  
	  
	  @Bean
	  DefaultConfigurationBean configBeanVilleComboBoxModel () {
	      return new DefaultConfigurationBean(VilleComboBoxModel.class,"villeComboBoxModel","initialiserModel");
	  }
	  
	  // Categorie
	  @Bean
	  DefaultConfigurationBean configBeanReferentielCategorieAjoutPanel () {
	      return new DefaultConfigurationBean(ReferentielCategorieAjoutPanel.class,"referentielcategorieAjoutPanel","init");
	  }

	  @Bean
	  DefaultConfigurationBean configBeanCategorieTableModel () {
	      return new DefaultConfigurationBean(CategorieTableModel.class,"categorieTableModel","initialiserModel");
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanCategorieComboBoxModel () {
	      return new DefaultConfigurationBean(CategorieComboBoxModel.class,"categorieComboBoxModel","initialiserModel");
	  }
	  
	  //Statut voyage
	  @Bean
	  DefaultConfigurationBean configBeanStatutVoyageComboBoxModel () {
	      return new DefaultConfigurationBean(StatutVoyageComboBoxModel.class,"statutVoyageComboBoxModel","initialiserModel");
	  }
	  
	  // Voyage
	  @Bean
	  DefaultConfigurationBean configBeanVoyageConsultationPanel () {
	      return new DefaultConfigurationBean(VoyageConsultationPanel.class,"voyageConsultationPanel","init");
	  }	 

	  @Bean
	  DefaultConfigurationBean configBeanVoyageCreationPanel () {
	      return new DefaultConfigurationBean(VoyageCreationPanel.class,"voyageCreationPanel","init");
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanVoyageModificationPanel () {
		  Map<String,Object> properties;
		  
		  properties=new HashMap ();
		  properties.put("mode",PersonneCreationPanel.MODE_MODIFICATION);
		  
	      return new DefaultConfigurationBean(VoyageCreationPanel.class,"voyageModificationPanel","init",properties);
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanVoyageTableModel () {
	      return new DefaultConfigurationBean(VoyageTableModel.class,"voyageTableModele","initialiserModel");
	  }
	  
	  //Adresse
	  @Bean
	  DefaultConfigurationBean configBeanAdresseConsultationPanel () {
	      return new DefaultConfigurationBean(AdresseConsultationPanel.class,"adresseConsultationPanel","init");
	  }	 

	  @Bean
	  DefaultConfigurationBean configBeanAdresseCreationPanel () {
	      return new DefaultConfigurationBean(AdresseCreationPanel.class,"adresseCreationPanel","init");
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanAdresseModificationPanel () {
		  Map<String,Object> properties;
		  
		  properties=new HashMap ();
		  properties.put("mode",PersonneCreationPanel.MODE_MODIFICATION);
		  
	      return new DefaultConfigurationBean(AdresseCreationPanel.class,"adresseModificationPanel","init",properties);
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanAdresseTableModel () {
	      return new DefaultConfigurationBean(AdresseTableModel.class,"adresseTableModele","initialiserModel");
	  }

	  
	  @Bean
	  DefaultConfigurationBean configBeanChoixAdresseDialog () {
	      return new DefaultConfigurationBean(ChoixAdresseDialog.class,"choixAdresseDialog","initialiserModel");
	  }
	  
	  //Activite
	  @Bean
	  DefaultConfigurationBean configBeanActiviteConsultationPanel () {
	      return new DefaultConfigurationBean(ActiviteConsultationPanel.class,"activiteConsultationPanel","init");
	  }	 
	  
	  @Bean
	  DefaultConfigurationBean configBeanActiviteCreationPanel () {
	      return new DefaultConfigurationBean(ActiviteCreationPanel.class,"activiteCreationPanel","init");
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanActiviteModificationPanel () {
		  Map<String,Object> properties;
		  
		  properties=new HashMap ();
		  properties.put("mode",PersonneCreationPanel.MODE_MODIFICATION);
		  
	      return new DefaultConfigurationBean(ActiviteCreationPanel.class,"activiteModificationPanel","init",properties);
	  }
	  
	  @Bean
	  DefaultConfigurationBean configBeanActiviteTableModel () {
	      return new DefaultConfigurationBean(ActiviteTableModel.class,"activiteTableModele","initialiserModel");
	  }

}
