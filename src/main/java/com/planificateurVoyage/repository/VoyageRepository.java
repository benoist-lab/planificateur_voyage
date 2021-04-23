package com.planificateurVoyage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planificateurVoyage.model.Voyage;

public interface VoyageRepository extends JpaRepository <Voyage,Long> {

}
