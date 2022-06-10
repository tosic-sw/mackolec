package com.vet.mackolec.services.impl;

import java.util.List;

import com.vet.mackolec.models.Disease;
import com.vet.mackolec.repositories.DiseaseRepository;
import com.vet.mackolec.services.DiseaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	private DiseaseRepository diseaseRepository;
	

	@Override
	public List<Disease> getDiseasesWithSymptomsAndMedicines() {
		List<Disease> diseases = diseaseRepository.findAllWithSymptoms();
		
		for (Disease disease : diseases) {
			Disease diseaseWithMedicine = diseaseRepository.findOneWithMedicines(disease.getId());
			disease.setMedicine(diseaseWithMedicine.getMedicine());
		}
		
		return diseases;
	}


	@Override
	public List<Disease> findAll() {
		return diseaseRepository.findAll();
	}

}
