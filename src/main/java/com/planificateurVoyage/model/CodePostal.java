package com.planificateurVoyage.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name="R_CODE_POSTAL")
public class CodePostal implements IDataModel {

	@Column (name="id_code_postal")
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="code_postal")
	private String codePostal;
	
	@Column(name="ville")
	private String ville;


	@ManyToOne
	@JoinColumn (name="id_pays")
	Pays pays;
	
	@OneToMany (mappedBy="codePostal")
	@JsonBackReference
	private Set<Adresse> adresse;
	
	
	public String getCodePostalLigne () {
		return getCodePostal () + " " + getVille () + " " + getPays().getPays();
	}
	
}
