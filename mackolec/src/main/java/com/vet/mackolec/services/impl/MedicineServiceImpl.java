package com.vet.mackolec.services.impl;

import java.util.List;

import com.vet.mackolec.models.Medicine;
import com.vet.mackolec.repositories.MedicineRepository;
import com.vet.mackolec.services.MedicineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	private MedicineRepository medicineRepository;
	
	@Override
	public List<Medicine> findAll() {
		return medicineRepository.findAll();
	}

}
