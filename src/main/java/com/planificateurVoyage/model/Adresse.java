package com.planificateurVoyage.model;

import java.util.Set;

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
@Table(name="F_ADRESSE")
public class Adresse implements IDataModel {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name="id_adresse")
	private Long id;
	
	@Column(name="libelle")
	private String libelle;

	@Column(name="ligne1")
	private String ligne1;
	
	@Column(name="ligne2")
	private String ligne2;
	
	@Column(name="ligne3")
	private String ligne3;
	
	
	@ManyToOne
	@JoinColumn (name="id_code_postal")
	CodePostal codePostal;
	
	@OneToMany (mappedBy="adresse")
	@JsonBackReference(value="adresse-activite")
	private Set<Activite> activite;
	
	public String getAdresseLigne () {
		String resultat=getLibelle ();
		
		if ((getLigne1()!=null) && (!getLigne1().isEmpty())) {
			resultat+=" "+getLigne1();
		}
		
		if ((getLigne2()!=null) && (!getLigne2().isEmpty())) {
			resultat+=" "+getLigne2();
		}
		
		if ((getLigne3()!=null) && (!getLigne3().isEmpty())) {
			resultat+=" "+getLigne3();
		}

		if (getCodePostal()!=null) {
			resultat+=" "+getCodePostal().getCodePostalLigne();
		}
		
		return resultat;
	}
	
}
