package com.vet.mackolec.services.impl;

import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.repositories.TherapyRepository;
import com.vet.mackolec.services.TherapyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TherapyServiceImpl implements TherapyService {

	@Autowired
	private TherapyRepository therapyRepository;
	
	@Override
	public Therapy getLastTherapyOfCat(String jmbm) {
		Pageable pageable = PageRequest.of(0, 1);
		Page<Therapy> lastTherapy = therapyRepository.getLastTherapyOfCat(jmbm, pageable);
		if(lastTherapy.isEmpty())
			return null;
		return lastTherapy.getContent().get(0);
	}

	@Override
	public void save(Therapy therapy) {
		therapyRepository.save(therapy);
	}
}
