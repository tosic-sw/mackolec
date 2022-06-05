package com.vet.mackolec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vet.mackolec.models.HospitalizedCat;

@Repository
public interface HospitalizedCatRepository extends JpaRepository<HospitalizedCat, Long>{
	
	@Query("select hc from HospitalizedCat hc where hc.cat.jmbm = ?1")
	HospitalizedCat findOneByJmbm(String jmbm);
}
