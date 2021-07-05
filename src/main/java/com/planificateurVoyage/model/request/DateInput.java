package com.planificateurVoyage.model.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DateInput  {
	  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	  private  Date date;
}

