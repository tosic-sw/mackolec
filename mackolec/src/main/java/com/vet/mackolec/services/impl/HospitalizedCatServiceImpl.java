package com.vet.mackolec.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.mackolec.models.HospitalizedCat;
import com.vet.mackolec.repositories.HospitalizedCatRepository;
import com.vet.mackolec.services.HospitalizedCatService;

@Service
public class HospitalizedCatServiceImpl implements HospitalizedCatService {

	@Autowired
	private HospitalizedCatRepository hospitalizedCatRepository;
	
	@Override
	public HospitalizedCat findOneByJmbm(String jmbm) {
		return hospitalizedCatRepository.findOneByJmbm(jmbm);
	}

	@Override
	public void save(HospitalizedCat hospCat) {
		hospitalizedCatRepository.save(hospCat);
	}

}
