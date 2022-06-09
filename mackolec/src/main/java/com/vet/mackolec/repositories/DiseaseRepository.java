package com.vet.mackolec.repositories;

import java.util.List;

import com.vet.mackolec.models.Disease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiseaseRepository extends JpaRepository<Disease, Long>{

	@Query("select distinct disease from Disease disease join fetch disease.symptoms")
	List<Disease> findAllWithSymptoms();

	@Query("select disease from Disease disease join fetch disease.medicine where disease.id = ?1")
	Disease findOneWithMedicines(Long id);

}
