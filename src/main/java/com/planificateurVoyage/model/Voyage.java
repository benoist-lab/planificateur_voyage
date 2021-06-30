package com.planificateurVoyage.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinTable;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="F_VOYAGE")
public class Voyage implements IDataModel {
	
	 
	@Column(name="id_voyage")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@Column(name="id_libelle")
    private String libelle;
    
	@Column(name="id_description")
    private String description;
	
	@ManyToOne
	@JoinColumn (name="id_statut")
	private StatutVoyage statut;
    
    @OneToMany (mappedBy="voyage",fetch = FetchType.EAGER)
//    @JsonBackReference(value="voyage-personnevoyage")
    Set<PersonneVoyage> personneVoyage=new HashSet<>();
    
    @OneToMany (mappedBy="voyage", fetch = FetchType.EAGER)
//  @JsonBackReference(value="voyage-activiteVoyage")
    Set<ActiviteVoyage> activiteVoyage=new HashSet<> ();
  
    

}
