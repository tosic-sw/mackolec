package com.vet.mackolec.services;

import com.vet.mackolec.models.HospitalizedCat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalizedCatService {

	HospitalizedCat findOneByJmbm(String jmbm);
	
	Page<HospitalizedCat> search(String search, Pageable pageable);

	void save(HospitalizedCat hospCat);
}
