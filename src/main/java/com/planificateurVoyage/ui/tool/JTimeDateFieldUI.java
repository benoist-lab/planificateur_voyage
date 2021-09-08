package com.planificateurVoyage.ui.tool;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

public class JTimeDateFieldUI extends JPanel {
	
	private JTimeSpinnerUI timeSpinner;
	private JDatePickerUI datePicker;
	
	public JTimeDateFieldUI () {
		super ();
		
		add(getTimeSpinner ());
		add(getDatePicker ());
		setDate (new Date ());
	}
	
	protected JTimeSpinnerUI getTimeSpinner () {
		if (timeSpinner==null) {
			timeSpinner=new JTimeSpinnerUI();
		}
		return timeSpinner;
	}
	
	protected JDatePickerUI getDatePicker () {
		if (datePicker==null) {
			datePicker=new JDatePickerUI ();
		}
		return datePicker;
	}
	
	public void setDate (Date date) {
		getTimeSpinner ().setTime(date);
		getDatePicker ().setDate(date);
	}

	public Date getDate () {
		
		Calendar date = Calendar.getInstance();
		Calendar resultat = Calendar.getInstance();

		date.setTime(getDatePicker ().getDate());
		resultat.setTime(getTimeSpinner ().getTime());
		
		resultat.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH));
		
		return resultat.getTime();
	}
}
