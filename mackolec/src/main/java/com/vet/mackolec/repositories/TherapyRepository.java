package com.vet.mackolec.repositories;

import com.vet.mackolec.models.Therapy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapyRepository extends JpaRepository<Therapy, Long>{

	@Query("select therapy from Therapy therapy where therapy.cat.jmbm = ?1 order by therapy.date desc")
	Page<Therapy> getLastTherapyOfCat(String jmbm, Pageable pageable);

}
