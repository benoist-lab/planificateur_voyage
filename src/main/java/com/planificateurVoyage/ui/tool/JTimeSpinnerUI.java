package com.planificateurVoyage.ui.tool;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class JTimeSpinnerUI extends JSpinner {
	

	public JTimeSpinnerUI () {
		super ();
		
		setModel(new SpinnerDateModel(new Date (), null, null, Calendar.HOUR_OF_DAY));
		setEditor(new JSpinner.DateEditor(this, "HH:mm"));
	}
	
	public void setTime (Date time) {
		getModel().setValue(time);
	}
	
	public Date getTime () {
		return ((SpinnerDateModel) getModel ()).getDate();
	}


}
