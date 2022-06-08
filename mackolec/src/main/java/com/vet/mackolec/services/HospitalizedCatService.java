package com.vet.mackolec.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vet.mackolec.models.HospitalizedCat;

public interface HospitalizedCatService {

	HospitalizedCat findOneByJmbm(String jmbm);
	
	Page<HospitalizedCat> search(String search, Pageable pageable);
}
