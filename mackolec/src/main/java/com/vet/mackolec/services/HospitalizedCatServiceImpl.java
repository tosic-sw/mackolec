package com.vet.mackolec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vet.mackolec.models.HospitalizedCat;
import com.vet.mackolec.repositories.HospitalizedCatRepository;

@Service
public class HospitalizedCatServiceImpl implements HospitalizedCatService {

	@Autowired
	private HospitalizedCatRepository hospitalizedCatRepository;
	
	@Override
	public HospitalizedCat findOneByJmbm(String jmbm) {
		return hospitalizedCatRepository.findOneByJmbm(jmbm);
	}

	@Override
	public Page<HospitalizedCat> search(String search, Pageable pageable) {
		return hospitalizedCatRepository.search(search, pageable);
	}

}
