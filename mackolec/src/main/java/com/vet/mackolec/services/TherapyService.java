package com.vet.mackolec.services;

import java.util.List;
import java.util.Map;

import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.enums.MedicineCategory;
import com.vet.mackolec.models.helper.ReportBreed;

public interface TherapyService {

	Therapy getLastTherapyOfCat(String jmbm);

	void save(Therapy therapy);
	
	Map<String, List<Therapy>> aquiredImmunityReport();
	
	Map<String, List<Therapy>> riskOfOrganDamageReport(MedicineCategory medicineCategory);
	
	Map<String, List<Therapy>> possibleChronicDiseaseReport();
		
	List<ReportBreed> catBreedReport();
		
}
