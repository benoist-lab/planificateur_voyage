package com.planificateurVoyage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.planificateurVoyage.model.ActiviteVoyage;
import com.planificateurVoyage.model.ActiviteVoyageKey;
import com.planificateurVoyage.model.PersonneVoyage;

public interface ActiviteVoyageRepository extends JpaRepository <ActiviteVoyage,ActiviteVoyageKey> {

	public List<ActiviteVoyage> findByActiviteId (Long id);
	public List<ActiviteVoyage> findByVoyageId (Long id);

}
