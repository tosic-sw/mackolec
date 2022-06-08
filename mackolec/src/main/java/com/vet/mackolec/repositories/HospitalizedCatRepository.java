package com.vet.mackolec.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vet.mackolec.models.HospitalizedCat;

@Repository
public interface HospitalizedCatRepository extends JpaRepository<HospitalizedCat, Long>{
	
	@Query("select hc from HospitalizedCat hc where hc.cat.jmbm = ?1")
	HospitalizedCat findOneByJmbm(String jmbm);
	
	@Query("select hc from HospitalizedCat hc where "
			+ " :search = '' "
			+ "or lower(hc.cat.jmbm) like lower(concat('%', :search, '%')) "
			+ "or lower(hc.cat.name) like lower(concat('%', :search, '%')) ")
	Page<HospitalizedCat> search(@Param("search") String search, Pageable pageable);
	
}
