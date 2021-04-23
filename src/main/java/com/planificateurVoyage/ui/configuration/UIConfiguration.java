package com.planificateurVoyage.ui.configuration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

import org.beryx.awt.color.ColorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.planificateurVoyage.ui.MainFrame;


public class UIConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(UIConfiguration.class);
    
    @Value("${ui.Panel.background}")
    //@Value("#{systemProperties['ui.Panel.background'] ?: 'WHITE'}")
    private String panelBackground;
    
    @Value("${ui.Button.background}")
    private String buttonBackground;
    
    
    @Value("${ui.focus.color}")
    private String focusColor;
    
    
    @Value("${ui.font.police}")
    private String fontPolice;
    
    @Value("${ui.font.size}")
    private int fontSize;
    
    @Value("${ui.font.TitledBorder.size}")
    private int fontSizeTitledBorder;
    
    @Value("${ui.font.size.titre}")
    private int fontSizeTitre;
    
    @Value("${ui.font.size.titre1}")
    private int fontSizeTitre1;
    
    
    @Value("${ui.font.size.small}")
    private int fontSizeSmall;
    
    @Value ("${ui.font.size.large}")
    private int fontSizeLarge;
    
    
    private Font fontDefault=null;
    public Font getFontDefault ()
    {
    	if (fontDefault==null) {
    		fontDefault=new Font(fontPolice, Font.PLAIN, fontSize);
    	}
    	
    	return fontDefault;
    }

    
    private Font fontTitledBorder=null;
    public Font getFontTitledBorder ()
    {
    	if (fontTitledBorder==null) {
    		fontTitledBorder=new Font(fontPolice, Font.ITALIC, fontSizeTitledBorder);
    	}
    	
    	return fontTitledBorder;
    }

    
    private Font fontTitle=null;
    public Font getFontTitle (){
    	if (fontTitle==null) {
    		fontTitle=new Font(fontPolice, Font.BOLD, fontSizeTitre);
    	}
    	
    	return fontTitle;
    }
    
    
    private Font fontTitle1=null;
    private Font getFontTitle1 (){
    	if (fontTitle1==null) {
    		fontTitle1=new Font(fontPolice, Font.BOLD, fontSizeTitre1);
    	}
    	
    	return fontTitle1;
    }
    
    private Font fontSmall;
    private Font getFontSmall () {
    	
    	if (fontSmall==null) {
    		fontSmall=new Font(fontPolice, Font.PLAIN, fontSizeSmall);
    	}
    	return fontSmall;
    }
    
    private Font fontLarge;
    private Font getFontLarge () {
    	
    	if (fontLarge==null) {
    		fontLarge=new Font(fontPolice, Font.PLAIN, fontSizeLarge);
    	}
    	
    	return fontLarge;
    }
    		
	public void initialiser () {

		logger.info("initialisation de la charte Graphique");


		initUIFont ();
		
		initUIBackground ();

		initUIComponentDimension ();

		UIManager.put("focus.color", ColorFactory.valueOf(focusColor));
		
		//printUIManagerKeys();
		
	}

	protected void initUIFont () {
		
		//Font prédéfinies
		UIManager.put("font.small",getFontSmall ());
		UIManager.put("font.large",getFontLarge ());
		UIManager.put("Label.font.titre", getFontTitle ());
		UIManager.put("Label.font.titre.1", getFontTitle1 ());

		//Look and Feel
		UIManager.put("Label.font", getFontDefault ());
		UIManager.put("TextField.font", getFontDefault ());
		UIManager.put("FormattedTextField.font", getFontDefault ());
		UIManager.put("PasswordField.font", getFontDefault ());
		UIManager.put("CheckBox.font", getFontDefault ());
		UIManager.put("ComboBox.font", getFontDefault ());
		UIManager.put("RadioButton.font", getFontDefault ());
		UIManager.put("List.font", getFontDefault ());
		UIManager.put("Spinner.font", getFontDefault ());
		UIManager.put("EditorPane.font", getFontDefault ());
		UIManager.put("TextPane.font", getFontDefault ());
		UIManager.put("TextArea.font", getFontDefault ());

		UIManager.put("Button.font", getFontDefault ());
		UIManager.put("ToggleButton.font", getFontDefault ());

		UIManager.put("Table.font", getFontDefault ());
		UIManager.put("TableHeader.font", getFontDefault ());

		UIManager.put("Tree.font", getFontDefault ());

		UIManager.put("Slider.font", getFontDefault ());
		UIManager.put("ProgressBar.font", getFontDefault ());
		UIManager.put("ColorChooser.font", getFontDefault ());
		UIManager.put("OptionPane.font", getFontDefault ());
		
		UIManager.put("MenuBar.font", getFontDefault ());
		UIManager.put("Menu.font", getFontDefault ());
		UIManager.put("MenuItem.font", getFontDefault ());
		UIManager.put("RadioButtonMenuItem.font", getFontDefault ());
		UIManager.put("CheckBoxMenuItem.font", getFontDefault ());
		
		UIManager.put("Panel.font", getFontDefault ());
		UIManager.put("Viewport.font", getFontDefault ());
		UIManager.put("TabbedPane.font", getFontDefault ());
		UIManager.put("PopupMenu.font", getFontDefault ());
		UIManager.put("ScrollPane.font", getFontDefault ());
		UIManager.put("ToolBar.font", getFontDefault ());
		
		UIManager.put("ToolTip.font", getFontDefault ());

		
		UIManager.put("TitledBorder.font", getFontTitledBorder ());
		
		
		UIManager.put("InternalFrame.titleFont", getFontDefault ());
		UIManager.put("DesktopIcon.font", getFontDefault ());

		
	}
	
	protected void initUIBackground () {
		UIManager.put("Panel.background", ColorFactory.valueOf(panelBackground));
		UIManager.put("Viewport.background", ColorFactory.valueOf(panelBackground));
		
		UIManager.put("Button.background", ColorFactory.valueOf(buttonBackground));
	}
	
	
	protected void initUIComponentDimension () {
//		private static final int TAILLE_CARACTERE=12;		>>>>>			private String fontSize;
		
		UIManager.put("Label.dimension", new Dimension (fontSize*10,fontSize+6));
		UIManager.put("Label.dimension.small", new Dimension (fontSize*5,fontSize+6));
		UIManager.put("Label.dimension.large", new Dimension (fontSize*20,fontSize+6));
		UIManager.put("TextField.dimension", new Dimension (fontSize*15,fontSize+6));
		UIManager.put("TextField.dimension.large", new Dimension (fontSize*45,fontSize+6));
		UIManager.put("CheckBox.dimension", new Dimension (fontSize*15,fontSize+6));
		UIManager.put("TextArea.dimension", new Dimension (fontSize*45,fontSize*5+6));
		UIManager.put("ComboBox.dimension", new Dimension (fontSize*15,fontSize+6));

		
		UIManager.put("Button.dimension", new Dimension (fontSize*7,fontSize+6));
		UIManager.put("Button.dimension.large", new Dimension (fontSize*10,fontSize+6));
		UIManager.put("Button.dimension.icon", new Dimension (50,50));

		
	}
	
	

	
	
	 private void printUIManagerKeys() {
		  UIDefaults defaults = UIManager.getDefaults();
		  Enumeration keysEnumeration = defaults.keys();
		  ArrayList keysList = Collections.list(keysEnumeration);
		  for (Object key : keysList) {
		   //if (defaults.getString(key) != null) {

		    logger.info(" - "+key + " - " + defaults.getString(key));
		   //}
		  }

	}

}
