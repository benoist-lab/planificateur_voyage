package com.planificateurVoyage.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.planificateurVoyage.model.Activite;
import com.planificateurVoyage.model.Adresse;
import com.planificateurVoyage.model.Categorie;
import com.planificateurVoyage.model.CodePostal;
import com.planificateurVoyage.model.Pays;
import com.planificateurVoyage.model.Personne;
import com.planificateurVoyage.model.PersonneVoyage;
import com.planificateurVoyage.model.StatutVoyage;
import com.planificateurVoyage.model.Voyage;
import com.planificateurVoyage.model.request.ActiviteCreateRequest;
import com.planificateurVoyage.model.request.AdresseCreateRequest;
import com.planificateurVoyage.model.request.CategorieCreateRequest;
import com.planificateurVoyage.model.request.CodePostalCreateRequest;
import com.planificateurVoyage.model.request.PaysCreateRequest;
import com.planificateurVoyage.model.request.PersonneCreateRequest;
import com.planificateurVoyage.model.request.PersonneVoyageAddRequest;
import com.planificateurVoyage.model.request.StatutVoyageCreateRequest;
import com.planificateurVoyage.model.request.VoyageCreateRequest;
import com.planificateurVoyage.service.ActiviteService;
import com.planificateurVoyage.service.AdresseService;
import com.planificateurVoyage.service.CategorieService;
import com.planificateurVoyage.service.CodePostalService;
import com.planificateurVoyage.service.PaysService;
import com.planificateurVoyage.service.PersonneService;
import com.planificateurVoyage.service.StatutVoyageService;
import com.planificateurVoyage.service.VoyageService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class BaseInitialiser {
	
	private static final Boolean INITIALISATION_ACTIVE=true;

    private static final Logger logger = LoggerFactory.getLogger(BaseInitialiser.class);
	
	private final PersonneService personneService;
    
	private final VoyageService voyageService;
	
	private final PaysService paysService;
	
	private final CodePostalService codePostalService;
	
	private final CategorieService categorieService;
	
	private final StatutVoyageService statutVoyageService;
	
	private final AdresseService adresseService;
	
	private final ActiviteService activiteService;
	
	
	
	public void initialiserBase () {
		boolean initialisationRequise;
		
		Long idDuboisBertrand=null;
		Long idMartinHenri=null;
		Long idSachinSimon=null;
		Long idVilleBoisPaul=null;
		Long idLegenecJacques=null;
		Long idDupuisYvette=null;
		
		Long idVoyagePise=null;
		Long idWeekendConquet=null;
		Long idEscaleBrest=null;
		
		Long idFrance=null;
		Long idBelgique=null;
		Long idSuisse=null;
		Long idItalie=null;
		
		Long idCodePostalBrest=null;
		Long idCodePostalQuimper=null;
		Long idCodePostalLeJuch=null;
		Long idCodePostalKerlaz=null;
		Long idCodePostalPouldergat=null;
		Long idCodePostalOrly=null;
		Long idCodePostalPise=null;
		
		Long idCategorieTransport=null;
		Long idCategorieHebergement=null;
		Long idCategorieRestauration=null;
		Long idCategorieLoisir=null;
		
		Long idNonRegle=null;
		Long idRegle=null;
		Long idAnnule=null;
		
		
		Long idAdresse1=null;
		Long idAdresse2=null;
		Long idAdresse3=null;
		Long idAdresse4=null;
		Long idAdresse5=null;
		
		Long idActivitePiscinePlouf=null;
		
		Personne personne;
		Voyage voyage;
		PersonneVoyage personneVoyage;
		Pays pays;
		CodePostal codePostal;
		Categorie categorie;
		StatutVoyage statutVoyage;
		Adresse adresse;
		Activite activite;

		
		PersonneCreateRequest personneCreateRequest;
		VoyageCreateRequest voyageCreateRequest;
		PersonneVoyageAddRequest personneVoyageAddRequest;
		PaysCreateRequest paysCreateRequest;		
		CodePostalCreateRequest codePostalCreateRequest;
		CategorieCreateRequest categorieCreateRequest;
		StatutVoyageCreateRequest statutVoyageCreateRequest;
		AdresseCreateRequest adresseCreateRequest;
		ActiviteCreateRequest activiteCreateRequest;
		
		
		if (INITIALISATION_ACTIVE)
		{
		
			logger.info("  *** INITIALISATION DE LA BASE DE DONNEES ***");
			
			initialisationRequise=(personneService.readPersonnes().size()==0);
					
			
			
			
			if (initialisationRequise)
			{
				logger.info(" - initialisation des personnes");
				
				personneCreateRequest=new PersonneCreateRequest ();

				// Dubois Bertrand
				personneCreateRequest.setNom("Dubois");
				personneCreateRequest.setPrenom("Bertrand");
				personne=personneService.createPersonne(personneCreateRequest);
				idDuboisBertrand=personne.getId();
				logger.info(" Dubois Bertrand="+idDuboisBertrand);
				
			
				// Martin Henri
				personneCreateRequest.setNom("Martin");
				personneCreateRequest.setPrenom("Henri");
				personne=personneService.createPersonne(personneCreateRequest);
				idMartinHenri=personne.getId();
				logger.info(" Martin Henri="+idMartinHenri);
				
				// Sachin Simon
				personneCreateRequest.setNom("Sachin");
				personneCreateRequest.setPrenom("Simon");
				personne=personneService.createPersonne(personneCreateRequest);
				idSachinSimon=personne.getId();
				logger.info(" Sachin Simon="+idSachinSimon);

				// VilleBois Paul
				personneCreateRequest.setNom("VilleBois");
				personneCreateRequest.setPrenom("Paul");
				personne=personneService.createPersonne(personneCreateRequest);
				idVilleBoisPaul=personne.getId();
				logger.info(" VilleBois Paul="+idVilleBoisPaul);

				// Legenec Jacques
				personneCreateRequest.setNom("Legenec");
				personneCreateRequest.setPrenom("Jacques");
				personne=personneService.createPersonne(personneCreateRequest);
				idLegenecJacques=personne.getId();
				logger.info(" Legenec Jacques="+idLegenecJacques);

				// Dupuis Yvette
				personneCreateRequest.setNom("Dupuis");
				personneCreateRequest.setPrenom("Yvette");
				personne=personneService.createPersonne(personneCreateRequest);
				idDupuisYvette=personne.getId();
				logger.info(" Dupuis Yvette="+idDupuisYvette);

				
				logger.info("    ---> OK ");
			
				
			
				logger.info(" - initialisation des voyages");

				voyageCreateRequest= new VoyageCreateRequest ();
				
				// Voyage à Pise
				voyageCreateRequest.setLibelle("Voyage à Pise");
				voyageCreateRequest.setDescription("jolie séjour à pise et promenade romantique.");
				voyage=voyageService.createVoyage(voyageCreateRequest);
				idVoyagePise=voyage.getId();
				logger.info(" Voyage à pise="+idVoyagePise);
				
				// Week-end au Conquet
				voyageCreateRequest.setLibelle("Week-end au Conquet");
				voyageCreateRequest.setDescription("week-end au bord de mer.");
				voyage=voyageService.createVoyage(voyageCreateRequest);
				idWeekendConquet=voyage.getId();
				logger.info(" Week-end au Conquet="+idWeekendConquet);
				
				// Escale à Brest
				voyageCreateRequest.setLibelle("Escale à Brest");
				voyageCreateRequest.setDescription("séjour d'une semaine à Brest et visite des environs.");
				voyage=voyageService.createVoyage(voyageCreateRequest);
				idEscaleBrest=voyage.getId();
				logger.info(" Escale à Brest="+idEscaleBrest);
				
				logger.info("    ---> OK ");

			
				logger.info(" - initialisation des personnes d'un voyage");
				
				personneVoyageAddRequest= new PersonneVoyageAddRequest ();
				
				// venise
				personneVoyageAddRequest.setVoyageId(idVoyagePise);
				personneVoyageAddRequest.setPersonneId(idMartinHenri);				
				personneVoyage=voyageService.addPersonneToVoyage(personneVoyageAddRequest);
				logger.info(" Voyage à Venise= + "+personneVoyage.getPersonneVoyageKey().getPersonneId());
				
				personneVoyageAddRequest.setVoyageId(idVoyagePise);
				personneVoyageAddRequest.setPersonneId(idDupuisYvette);				
				personneVoyage=voyageService.addPersonneToVoyage(personneVoyageAddRequest);
				logger.info(" Voyage à Venise= + "+personneVoyage.getPersonneVoyageKey().getPersonneId());
			
				
				//conquet
				personneVoyageAddRequest.setVoyageId(idWeekendConquet);
				personneVoyageAddRequest.setPersonneId(idDuboisBertrand);				
				personneVoyage=voyageService.addPersonneToVoyage(personneVoyageAddRequest);
				logger.info(" Week-end au Conquet= + "+personneVoyage.getPersonneVoyageKey().getPersonneId());
				
				//brest
				personneVoyageAddRequest.setVoyageId(idEscaleBrest);
				personneVoyageAddRequest.setPersonneId(idSachinSimon);				
				personneVoyage=voyageService.addPersonneToVoyage(personneVoyageAddRequest);
				logger.info(" Escale à Brest= + "+personneVoyage.getPersonneVoyageKey().getPersonneId());
	
				personneVoyageAddRequest.setVoyageId(idEscaleBrest);
				personneVoyageAddRequest.setPersonneId(idVilleBoisPaul);				
				personneVoyage=voyageService.addPersonneToVoyage(personneVoyageAddRequest);
				logger.info(" Escale à Brest= + "+personneVoyage.getPersonneVoyageKey().getPersonneId());
	
				personneVoyageAddRequest.setVoyageId(idEscaleBrest);
				personneVoyageAddRequest.setPersonneId(idLegenecJacques);				
				personneVoyage=voyageService.addPersonneToVoyage(personneVoyageAddRequest);
				logger.info(" Escale à Brest= + "+personneVoyage.getPersonneVoyageKey().getPersonneId());
	
				personneVoyageAddRequest.setVoyageId(idEscaleBrest);
				personneVoyageAddRequest.setPersonneId(idDuboisBertrand);				
				personneVoyage=voyageService.addPersonneToVoyage(personneVoyageAddRequest);
				logger.info(" Escale à Brest= + "+personneVoyage.getPersonneVoyageKey().getPersonneId());
	
			
				
				logger.info(" - initialisation des pays");
				
				paysCreateRequest=new PaysCreateRequest ();

				paysCreateRequest.setPays("France");
				pays=paysService.createPays(paysCreateRequest);
				idFrance=pays.getId();
				logger.info(" FRANCE="+idFrance);
				
				paysCreateRequest.setPays("Belgique");
				pays=paysService.createPays(paysCreateRequest);
				idBelgique=pays.getId();
				logger.info(" BELGIQUE="+idBelgique);
				
				paysCreateRequest.setPays("Suisse");
				pays=paysService.createPays(paysCreateRequest);
				idSuisse=pays.getId();
				logger.info(" SUISSE="+idSuisse);
				
				paysCreateRequest.setPays("Italie");
				pays=paysService.createPays(paysCreateRequest);
				idItalie=pays.getId();
				logger.info(" ITALIE="+idItalie);

			
				
				logger.info(" - initialisation des code postaux");
				
				codePostalCreateRequest=new CodePostalCreateRequest ();
				
//				pays=new Pays ();
//				pays.setId(idFrance);
				pays=paysService.readPays(idFrance);
				
				codePostalCreateRequest.setCodePostal("29200");
				codePostalCreateRequest.setVille("Brest");
				codePostalCreateRequest.setPays(pays);
				codePostal=codePostalService.createCodePostal(codePostalCreateRequest);
				idCodePostalBrest=codePostal.getId();
				logger.info(" BREST="+idCodePostalBrest);
				
				codePostalCreateRequest.setCodePostal("29200");
				codePostalCreateRequest.setVille("Quimper");
				codePostalCreateRequest.setPays(pays);
				codePostal=codePostalService.createCodePostal(codePostalCreateRequest);
				idCodePostalQuimper=codePostal.getId();
				logger.info(" QUIMPER="+idCodePostalQuimper);

				codePostalCreateRequest.setCodePostal("29100");
				codePostalCreateRequest.setVille("Le Juch");
				codePostalCreateRequest.setPays(pays);
				codePostal=codePostalService.createCodePostal(codePostalCreateRequest);
				idCodePostalLeJuch=codePostal.getId();
				logger.info(" LE JUCH="+idCodePostalLeJuch);

				codePostalCreateRequest.setCodePostal("29100");
				codePostalCreateRequest.setVille("Kerlaz");
				codePostalCreateRequest.setPays(pays);
				codePostal=codePostalService.createCodePostal(codePostalCreateRequest);
				idCodePostalKerlaz=codePostal.getId();
				logger.info(" KERLAZ="+idCodePostalKerlaz);

				codePostalCreateRequest.setCodePostal("29100");
				codePostalCreateRequest.setVille("Pouldergat");
				codePostalCreateRequest.setPays(pays);
				codePostal=codePostalService.createCodePostal(codePostalCreateRequest);
				idCodePostalPouldergat=codePostal.getId();
				logger.info(" PLOUDERGAT="+idCodePostalPouldergat);	
				
				
				codePostalCreateRequest.setCodePostal("94390");
				codePostalCreateRequest.setVille("Orly");
				codePostalCreateRequest.setPays(pays);
				codePostal=codePostalService.createCodePostal(codePostalCreateRequest);
				idCodePostalOrly=codePostal.getId();
				logger.info(" ORLY="+idCodePostalOrly);	
				
				

//				pays=new Pays ();
//				pays.setId(idItalie);
				pays=paysService.readPays(idItalie);


				codePostalCreateRequest.setCodePostal("56048");
				codePostalCreateRequest.setVille("Pisa");
				codePostalCreateRequest.setPays(pays);
				codePostal=codePostalService.createCodePostal(codePostalCreateRequest);
				idCodePostalPise=codePostal.getId();
				logger.info(" PISE="+idCodePostalPise);	
				


				logger.info(" - initialisation des cat\u00E9gorie");
				
				categorieCreateRequest=new CategorieCreateRequest ();

				
				categorieCreateRequest.setLibelle("Transport");
				categorie=categorieService.createCategorie(categorieCreateRequest);
				idCategorieTransport=categorie.getId();
				logger.info(" TRANSPORT="+idCategorieTransport);
				
				categorieCreateRequest.setLibelle("H\u00E9bergement");
				categorie=categorieService.createCategorie(categorieCreateRequest);
				idCategorieHebergement=categorie.getId();
				logger.info(" HEBERGEMENT="+idCategorieHebergement);
				
				categorieCreateRequest.setLibelle("Restauration");
				categorie=categorieService.createCategorie(categorieCreateRequest);
				idCategorieRestauration=categorie.getId();
				logger.info(" RESTAURATION="+idCategorieRestauration);
				
				categorieCreateRequest.setLibelle("Loisir");
				categorie=categorieService.createCategorie(categorieCreateRequest);
				idCategorieLoisir=categorie.getId();
				logger.info(" LOISIR="+idCategorieLoisir);
				

				logger.info(" - initialisation des statut voyage");
				
				statutVoyageCreateRequest=new StatutVoyageCreateRequest ();

				
				statutVoyageCreateRequest.setLibelle("Non r\u00E9gl\u00E9");
				statutVoyage=statutVoyageService.createStatutVoyage(statutVoyageCreateRequest);
				idNonRegle=statutVoyage.getId();
				logger.info(" NON REGLE="+idNonRegle);

				statutVoyageCreateRequest.setLibelle("R\u00E9gl\u00E9");
				statutVoyage=statutVoyageService.createStatutVoyage(statutVoyageCreateRequest);
				idRegle=statutVoyage.getId();
				logger.info(" REGLE="+idRegle);

				statutVoyageCreateRequest.setLibelle("Annul\u00E9");
				statutVoyage=statutVoyageService.createStatutVoyage(statutVoyageCreateRequest);
				idAnnule=statutVoyage.getId();
				logger.info(" ANNULE="+idAnnule);

				
				
				
				logger.info(" - initialisation des adresses");
				
				adresseCreateRequest=new AdresseCreateRequest ();
				
				codePostal=codePostalService.readCodePostal(idCodePostalOrly);
				
				adresseCreateRequest.setLibelle("Orly");
				adresseCreateRequest.setLigne1("Avenue Sud");
				adresseCreateRequest.setLigne2("");
				adresseCreateRequest.setLigne3("");
				adresseCreateRequest.setCodePostal(codePostal);
				adresse=adresseService.createAdresse(adresseCreateRequest);
				idAdresse1=adresse.getId();
				logger.info(" ADRESSE N°1="+idAdresse1);
				
				codePostal=codePostalService.readCodePostal(idCodePostalBrest);
				
				adresseCreateRequest.setLibelle("Appartement");
				adresseCreateRequest.setLigne1("28 rue du chemin");
				adresseCreateRequest.setLigne2("2eme \u00E9tage");
				adresseCreateRequest.setLigne3("gauche");
				adresseCreateRequest.setCodePostal(codePostal);
				adresse=adresseService.createAdresse(adresseCreateRequest);
				idAdresse2=adresse.getId();
				logger.info(" ADRESSE N°2="+idAdresse2);

				adresseCreateRequest.setLibelle("Piscine Plouf");
				adresseCreateRequest.setLigne1("rue du port");
				adresseCreateRequest.setLigne2("");
				adresseCreateRequest.setLigne3("");
				adresseCreateRequest.setCodePostal(codePostal);
				adresse=adresseService.createAdresse(adresseCreateRequest);
				idAdresse3=adresse.getId();
				logger.info(" ADRESSE N°3="+idAdresse3);

				
				adresseCreateRequest.setLibelle("Theatre des Stars");
				adresseCreateRequest.setLigne1("96 avenue du Chateau");
				adresseCreateRequest.setLigne2("");
				adresseCreateRequest.setLigne3("CEDEX 29211");
				adresseCreateRequest.setCodePostal(codePostal);
				adresse=adresseService.createAdresse(adresseCreateRequest);
				idAdresse4=adresse.getId();
				logger.info(" ADRESSE N°4="+idAdresse4);
				
				codePostal=codePostalService.readCodePostal(idCodePostalPise);
				
				adresseCreateRequest.setLibelle("Parco acquatico ");
				adresseCreateRequest.setLigne1("6 via del mare");
				adresseCreateRequest.setLigne2("");
				adresseCreateRequest.setLigne3("");
				adresseCreateRequest.setCodePostal(codePostal);
				adresse=adresseService.createAdresse(adresseCreateRequest);
				idAdresse5=adresse.getId();
				logger.info(" ADRESSE N°5="+idAdresse5);
				

				
				//idActivitePiscinePlouf
				
				Long idActiviteOrlyVol412=null;
				Long idActiviteAppartementBnB33=null;
				
				Long idActiviteTheatreDesStars=null;
				Long idActiviteOrlyVolI741i3=null;
				Long idActiviteParcoAcquatico=null;
				
				
				logger.info(" - initialisation des activit\u00E9s");
				
				activiteCreateRequest=new ActiviteCreateRequest ();
				
				categorie=categorieService.readCategorie(idCategorieTransport);
				adresse=adresseService.readAdresse(idAdresse1);
				
				activiteCreateRequest.setLibelle("Orly Vol 412");
				activiteCreateRequest.setCout(200);
				activiteCreateRequest.setForfaitaire(false);
				activiteCreateRequest.setDescription("Vol Paris-Brest");
				activiteCreateRequest.setCategorie(categorie);
				activiteCreateRequest.setAdresse(adresse);
				activite=activiteService.createActivite(activiteCreateRequest);
				idActiviteOrlyVol412=adresse.getId();
				logger.info(" ACTIVITE 'Orly Vol 412'="+idActiviteOrlyVol412);


				categorie=categorieService.readCategorie(idCategorieHebergement);
				adresse=adresseService.readAdresse(idAdresse2);
				
				activiteCreateRequest.setLibelle("Appartement BnB 33");
				activiteCreateRequest.setCout(50);
				activiteCreateRequest.setForfaitaire(true);
				activiteCreateRequest.setDescription("4 lits");
				activiteCreateRequest.setCategorie(categorie);
				activiteCreateRequest.setAdresse(adresse);
				activite=activiteService.createActivite(activiteCreateRequest);
				idActiviteAppartementBnB33=adresse.getId();
				logger.info(" ACTIVITE 'Appartement BnB 33'="+idActiviteAppartementBnB33);
				
				
				
				categorie=categorieService.readCategorie(idCategorieLoisir);
				adresse=adresseService.readAdresse(idAdresse3);
				
				activiteCreateRequest.setLibelle("Piscine Plouf");
				activiteCreateRequest.setCout(5.25);
				activiteCreateRequest.setForfaitaire(false);
				activiteCreateRequest.setDescription("journ\u00E9e à la piscine");
				activiteCreateRequest.setCategorie(categorie);
				activiteCreateRequest.setAdresse(adresse);
				activite=activiteService.createActivite(activiteCreateRequest);
				idActivitePiscinePlouf=adresse.getId();
				logger.info(" ACTIVITE 'Piscine Plouf'="+idActivitePiscinePlouf);

				
				categorie=categorieService.readCategorie(idCategorieLoisir);
				adresse=adresseService.readAdresse(idAdresse4);
				
				activiteCreateRequest.setLibelle("Th\u00E9atre des Stars");
				activiteCreateRequest.setCout(49.99);
				activiteCreateRequest.setForfaitaire(false);
				activiteCreateRequest.setDescription("soir\u00E9 au th\u00E9atre");
				activiteCreateRequest.setCategorie(categorie);
				activiteCreateRequest.setAdresse(adresse);
				activite=activiteService.createActivite(activiteCreateRequest);
				idActiviteTheatreDesStars=adresse.getId();
				logger.info(" ACTIVITE 'Th\u00E9atre des Stars'="+idActiviteTheatreDesStars);
				
				
				categorie=categorieService.readCategorie(idCategorieTransport);
				adresse=adresseService.readAdresse(idAdresse1);
				
				activiteCreateRequest.setLibelle("Orly Vol I741i3");
				activiteCreateRequest.setCout(1000);
				activiteCreateRequest.setForfaitaire(false);
				activiteCreateRequest.setDescription("Vol Paris-Pise");
				activiteCreateRequest.setCategorie(categorie);
				activiteCreateRequest.setAdresse(adresse);
				activite=activiteService.createActivite(activiteCreateRequest);
				idActiviteOrlyVolI741i3=adresse.getId();
				logger.info(" ACTIVITE 'Orly Vol I741i3'="+idActiviteOrlyVolI741i3);
				

				categorie=categorieService.readCategorie(idCategorieLoisir);
				adresse=adresseService.readAdresse(idAdresse5);
				
				activiteCreateRequest.setLibelle("Parco acquatico");
				activiteCreateRequest.setCout(20);
				activiteCreateRequest.setForfaitaire(false);
				activiteCreateRequest.setDescription("journ\u00E9e au parc nautique");
				activiteCreateRequest.setCategorie(categorie);
				activiteCreateRequest.setAdresse(adresse);
				activite=activiteService.createActivite(activiteCreateRequest);
				idActiviteParcoAcquatico=adresse.getId();
				logger.info(" ACTIVITE 'Parco acquatico'="+idActiviteParcoAcquatico);

				
				
				logger.info("    ---> INITIALISATION COMPLETE ");
			}
			else
			{
				logger.info("    ---> EXISTE");
	
			}
			
			logger.info("  *** FIN DE INITIALISATION DE LA BASE DE DONNEES ***");
			
		}
		else
		{
			logger.info("  *** INITIALISATION DE LA BASE DE DONNEES: désactivée ***");
		}
		
	}
	
}
