package com.vet.mackolec.services.impl;

import java.util.List;

import com.vet.mackolec.models.Symptom;
import com.vet.mackolec.repositories.SymptomRepository;
import com.vet.mackolec.services.SymptomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SymptomServiceImpl implements SymptomService{

	@Autowired
	private SymptomRepository symptomRepository;
	
	@Override
	public List<Symptom> getSymptoms(){
		return symptomRepository.findAll();
	}

	@Override
	public Symptom findOne(String name) {
		return symptomRepository.findByName(name);
	}
}
