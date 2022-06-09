package com.vet.mackolec.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vet.mackolec.models.Therapy;

@Repository
public interface TherapyRepository extends JpaRepository<Therapy, Long>{

	@Query("select t from Therapy t where "
			+ " :search = '' "
			+ "or lower(t.cat.jmbm) like lower(concat('%', :search, '%')) "
			+ "or lower(t.cat.name) like lower(concat('%', :search, '%')) "
			+ "or lower(t.disease.name) like lower(concat('%', :search, '%')) "
			+ "or lower(t.medicine.name) like lower(concat('%', :search, '%')) ")
	Page<Therapy> search(@Param("search") String search, Pageable pageable);
	
	@Query("select therapy from Therapy therapy where therapy.cat.jmbm = ?1 order by therapy.date desc")
	Page<Therapy> getLastTherapyOfCat(String jmbm, Pageable pageable);
	
}
