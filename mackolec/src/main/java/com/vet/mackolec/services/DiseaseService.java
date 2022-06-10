package com.vet.mackolec.services;

import java.util.List;

import com.vet.mackolec.models.Disease;

public interface DiseaseService {

	List<Disease> getDiseasesWithSymptomsAndMedicines();
	
	List<Disease> findAll();
}
