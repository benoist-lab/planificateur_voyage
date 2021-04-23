package com.planificateurVoyage.ui.tool;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocumentFilterTextRegex extends DocumentFilter {

	public enum MODE_TRANSFORMATION{
		 PREMIER_CARACTERE_MAJUSCULE,
		TOUT_CARACTERE_MAJUSCULE
	}
	
	private boolean premierCaractereMajuscule=false;
	private boolean tousCaractereMajuscule=false;

	private int maxCharacters=Integer.MAX_VALUE;
	private String regex;

	public void setModeTransformation (MODE_TRANSFORMATION mode) {
		if (mode==MODE_TRANSFORMATION.PREMIER_CARACTERE_MAJUSCULE) {
			premierCaractereMajuscule=true;
			tousCaractereMajuscule=false;
		} else if (mode==MODE_TRANSFORMATION.TOUT_CARACTERE_MAJUSCULE) {
			premierCaractereMajuscule=false;
			tousCaractereMajuscule=true;
		}
	}

	
	public DocumentFilterTextRegex (String regex) {
		super ();
		
		this.regex=regex;
	}
	
	public DocumentFilterTextRegex (String regex,MODE_TRANSFORMATION mode) {
		this (regex);
		
		this.regex=regex;
		setModeTransformation(mode);
	}
	
	public DocumentFilterTextRegex (String regex,int maxCharacters) {
		this (regex);
		
		this.maxCharacters=maxCharacters;
	}
	
	public DocumentFilterTextRegex (String regex,int maxCharacters,MODE_TRANSFORMATION mode) {
		this (regex);
		
		this.maxCharacters=maxCharacters;
		setModeTransformation(mode);
	}
	
	private String transformeSaisie (String text, int offs) {
		String strRell=text;
		
		if (tousCaractereMajuscule) {
			strRell=text.toUpperCase();
		} else if (premierCaractereMajuscule && (offs==0)) {
    		if (text.length()==1) {
    			strRell=text.toUpperCase();
    		} else {
    			strRell=text.substring(0, 1).toUpperCase()+text.substring(1);
    		}
    	}
    	
    	return strRell;
	}
	
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {

    	String strRell=transformeSaisie (str, offs);
    	
        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
        text += strRell;
        if ((fb.getDocument().getLength() + strRell.length() - length) <= maxCharacters && text.matches(regex)) {
            super.replace(fb, offs, length, strRell, a);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }

    }

    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {

      String strRell=transformeSaisie (str, offs);
    	

      String text = fb.getDocument().getText(0, fb.getDocument().getLength());
      text += strRell;
      if ((fb.getDocument().getLength() + strRell.length()) <= maxCharacters && text.matches(regex)) {
          super.insertString(fb, offs, strRell, a);
      } else {
          Toolkit.getDefaultToolkit().beep();
      }
      
    }

}
