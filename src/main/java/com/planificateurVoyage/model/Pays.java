package com.planificateurVoyage.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="R_PAYS")
public class Pays implements IDataModel {
	
	@Column(name="id_pays")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    
	@Column(name="pays")
    private String pays;
	
	
	
	@OneToMany (mappedBy="pays")
	@JsonBackReference
	Set<CodePostal> codePostal;
	
	
	
	@Override
	public String toString () {
		return getPays ();
	}
	

}
