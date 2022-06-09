package com.vet.mackolec.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vet.mackolec.models.Therapy;

public interface TherapyService {
	
	public Page<Therapy> search(String search, Pageable pageable);
	
	Therapy getLastTherapyOfCat(String jmbm);

	void save(Therapy therapy);
}
