package com.vet.mackolec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.repositories.TherapyRepository;

@Service
public class TherapyServiceImpl implements TherapyService {

	@Autowired
	private TherapyRepository therapyRepository;
	
	@Override
	public Page<Therapy> search(String search, Pageable pageable) {
		return therapyRepository.search(search, pageable);
	}

	
}
