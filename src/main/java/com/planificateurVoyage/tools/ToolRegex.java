package com.planificateurVoyage.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.planificateurVoyage.tools.exception.AdresseInvalidException;
import com.planificateurVoyage.tools.exception.CodePostalInvalidException;
import com.planificateurVoyage.tools.exception.NomInvalidException;
import com.planificateurVoyage.tools.exception.NombreInvalidException;
import com.planificateurVoyage.tools.exception.PaysInvalidException;
import com.planificateurVoyage.tools.exception.VilleInvalidException;

public class ToolRegex {
	
	public static final String REGEX_NOM="([\\-a-zA-Z\\séèçàâêîôûÉÈÇÂÊÎÔÛäëïöüÄËÏÖÜù'&])+"; 
	public static final String REGEX_PRENOM="([\\-a-zA-Z\\séèçàâêîôûÉÈÇÂÊÎÔÛäëïöüÄËÏÖÜù'&])*"; 
	
	public static final String REGEX_ALPHANUMERIQUE="([\\-\\+0-9a-zA-Z\\séèçàâêîôûÉÈÇÂÊÎÔÛäëïöüÄËÏÖÜù'])+"; 
	public static final String REGEX_NUMERIQUE="([\\-\\+0-9\\.])+"; 
	
	public static final String REGEX_LIGNE_ADRESSE_RNVP="([\\-0-9a-zA-Z\\séèçàâêîôûÉÈÇÂÊÎÔÛäëïöüÄËÏÖÜù'&°\\.\\,])*";
	public static final int TAILLE_LIGNE_ADRESSE_RNVP_MAXIMUM=38;
	
	public static final String REGEX_CODE_POSTAL="([\\-0-9A-Z\\s])+"; 
	public static int TAILLE_CODE_POSTAL_MAXIMUM=9;
	
	public static final String REGEX_VILLE="([\\-a-zA-Z\\séèçàâêîôûÉÈÇÂÊÎÔÛäëïöüÄËÏÖÜù'])+";
	public static final String REGEX_PAYS="([\\-A-Z\\sÉÈÇÂÊÎÔÛÄËÏÖÜ'])+";
	
	/**
	 * @throws NomInvalidException
	 */
	public static void valideNom (String donnee, String nomDonnee) {
        Pattern p = Pattern.compile(ToolRegex.REGEX_NOM);
        Matcher m = p.matcher(donnee);
        
        if (!m.matches()){
        	throw new NomInvalidException ("le format de '"+nomDonnee+"' est incorrect: majuscule, minuscule, espace, é, è, ç, à, â, ê, î, ô, û, É, È, Ç, Â, Ê, Î, Ô, Û, ä, ë, ï, ö, ü, Ä, Ë, Ï, Ö, Ü, ù, ', &, -");
        }
	}
	/**
	 * @throws NomInvalidException
	 */
	public static void validePrenom (String donnee, String nomDonnee) {
        Pattern p = Pattern.compile(ToolRegex.REGEX_PRENOM);
        Matcher m = p.matcher(donnee);
        
        if (!m.matches()){
        	throw new NomInvalidException ("le format de '"+nomDonnee+"' est incorrect: majuscule, minuscule, espace, é, è, ç, à, â, ê, î, ô, û, É, È, Ç, Â, Ê, Î, Ô, Û, ä, ë, ï, ö, ü, Ä, Ë, Ï, Ö, Ü, ù, ', &, -");
        }
	}
	
	/**
	 * @throws NomInvalidException
	 */
	public static void valideAlphanumerique (String donnee, String nomDonnee) {
        Pattern p = Pattern.compile(ToolRegex.REGEX_ALPHANUMERIQUE);
        Matcher m = p.matcher(donnee);
        
        if (!m.matches()){
        	throw new NomInvalidException ("le format de '"+nomDonnee+"' est incorrect: chiffre, majuscule, minuscule, espace, é, è, ç, à, â, ê, î, ô, û, É, È, Ç, Â, Ê, Î, Ô, Û, ä, ë, ï, ö, ü, Ä, Ë, Ï, Ö, Ü, ù, ', -, +");
        }
	}
	/**
	 * @throws NombreInvalidException
	 */
	public static void valideNumerique (String donnee, String nomDonnee) {
        Pattern p = Pattern.compile(ToolRegex.REGEX_NUMERIQUE);
        Matcher m = p.matcher(donnee);
        
        if (!m.matches()){
        	throw new NombreInvalidException ("le format de '"+nomDonnee+"' est incorrect: chiffre, -, +, .");
        }
	}
	
	/**
	 * norme RNVP
	 * 
	 * @throws AdresseInvalidException
	 */
	public static void valideLigneAdresseRNVP (String donnee, String nomDonnee) {
        Pattern p = Pattern.compile(ToolRegex.REGEX_LIGNE_ADRESSE_RNVP);
        Matcher m = p.matcher(donnee);
        
        if (!m.matches()){
        	throw new AdresseInvalidException ("le format de '"+nomDonnee+"' est incorrect: chiffre, majuscule, minuscule, espace, é, è, ç, à, â, ê, î, ô, û, É, È, Ç, Â, Ê, Î, Ô, Û, ä, ë, ï, ö, ü, Ä, Ë, Ï, Ö, Ü, ù, ', &, -, °, . , ','");
        }
        
        if (donnee.length()>TAILLE_LIGNE_ADRESSE_RNVP_MAXIMUM) {
        	throw new AdresseInvalidException ("le format de '"+nomDonnee+"' est incorrect: "+TAILLE_LIGNE_ADRESSE_RNVP_MAXIMUM+" caract\u00E8res maximum");
        }
	}
	
	/**
	 * @throws VilleInvalidException
	 */
	public static void valideVille (String donnee, String nomDonnee) {
        Pattern p = Pattern.compile(ToolRegex.REGEX_VILLE);
        Matcher m = p.matcher(donnee);
        
        if (!m.matches()){
        	throw new VilleInvalidException ("le format de '"+nomDonnee+"' est incorrect: majuscule, minuscule, espace, é, è, ç, à, â, ê, î, ô, û, É, È, Ç, Â, Ê, Î, Ô, Û, ä, ë, ï, ö, ü, Ä, Ë, Ï, Ö, Ü, ù, ', -");
        }
	}
	
	
	/**
	 * @throws CodePostalInvalidException
	 */
	public static void valideCodePostal (String donnee, String nomDonnee) {
        Pattern p = Pattern.compile(ToolRegex.REGEX_CODE_POSTAL);
        Matcher m = p.matcher(donnee);
        
        if (!m.matches()){
        	throw new CodePostalInvalidException ("le format de '"+nomDonnee+"' est incorrect: chiffre, majuscule, espace, -");
        }
        
        
        if (donnee.length()>TAILLE_CODE_POSTAL_MAXIMUM) {
        	throw new CodePostalInvalidException ("le format de '"+nomDonnee+"' est incorrect: "+TAILLE_CODE_POSTAL_MAXIMUM+" caract\u00E8res maximum");
        }
	}
	
	/**
	 * @throws PaysInvalidException
	 */
	public static void validePays (String donnee, String nomDonnee) {
        Pattern p = Pattern.compile(ToolRegex.REGEX_PAYS);
        Matcher m = p.matcher(donnee);
        
        if (!m.matches()){
        	throw new PaysInvalidException ("le format de '"+nomDonnee+"' est incorrect: majuscule, espace, É, È, Ç, Â, Ê, Î, Ô, Û, Ä, Ë, Ï, Ö, Ü, ', -");
        }
	}

}
