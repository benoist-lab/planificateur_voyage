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
@Table (name="R_CATEGORIE")
public class Categorie implements IDataModel {
	
	@Column (name="id_categorie")
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;
	
	
	@Column (name="libelle")
	private String libelle;

	@Override
	public String toString () {
		return getLibelle ();
	}
	
	@OneToMany (mappedBy="categorie")
	@JsonBackReference(value="categorie-activite")
	private Set<Activite> activite;
	

}
