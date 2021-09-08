package com.planificateurVoyage.ui.tool;

import java.util.Date;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/*
 * 	https://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component 
 *  https://mvnrepository.com/artifact/org.jdatepicker/jdatepicker/1.3.4
 */
public class JDatePickerUI extends JDatePickerImpl {
	

	
	public JDatePickerUI (){		
		super (new JDatePanelImpl(new UtilDateModel(),new PropertiesDatePicker()),new DateLabelFormatter());
		

	}
	
	public void setDate (Date date) {
		((UtilDateModel)getModel()).setValue(date);
	}
	
	public Date getDate () {
		return ((UtilDateModel)getModel()).getValue();
	}

}
