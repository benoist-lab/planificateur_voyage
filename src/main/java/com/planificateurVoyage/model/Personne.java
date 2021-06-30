package com.planificateurVoyage.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="F_PERSONNE",  uniqueConstraints={
		   @UniqueConstraint(columnNames={"nom", "prenom"})
		})

public class Personne implements IDataModel {
	 
	@Column(name="id_personne")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
	@Column(name="nom")
    private String nom;
    
	@Column(name="prenom")
    private String prenom;
    
    @OneToMany (mappedBy="personne")
    @JsonBackReference(value="personne-personnevoyage")
    Set<PersonneVoyage> personneVoyage;
    
}
