package com.vet.mackolec.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.dto.CatTherapiesReportDTO;
import com.vet.mackolec.models.enums.MedicineCategory;
import com.vet.mackolec.models.helper.ReportBreed;
import com.vet.mackolec.services.TherapyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reports")
public class ReportController {
	
	@Autowired
	private TherapyService therapyService;

	@GetMapping("/aquiredImmunity")
	public ResponseEntity<List<CatTherapiesReportDTO>> getCatsThatAquiredImmunityToDrug(){
		Map<String, List<Therapy>> aquiredImmunityReport = therapyService.aquiredImmunityReport();
		List<CatTherapiesReportDTO> catsAquiredImmunity = new ArrayList<>();
		
		aquiredImmunityReport.forEach((catName, therapies) -> {
			Cat cat = aquiredImmunityReport.get(catName).get(0).getCat();
			catsAquiredImmunity.add(new CatTherapiesReportDTO(cat, therapies));
		});
		
		return new ResponseEntity<>(catsAquiredImmunity, HttpStatus.OK);
	}
	
	@GetMapping("/riskOfOrganDamage/{medicineCategory}")
	public ResponseEntity<List<CatTherapiesReportDTO>> getCatsAtRiskOfOrganDamage(@PathVariable String medicineCategory){
		Map<String, List<Therapy>> aquiredImmunityReport = therapyService.riskOfOrganDamageReport(MedicineCategory.valueOf(medicineCategory));
		List<CatTherapiesReportDTO> catsAquiredImmunity = new ArrayList<>();
		
		aquiredImmunityReport.forEach((catName, therapies) -> {
			Cat cat = aquiredImmunityReport.get(catName).get(0).getCat();
			catsAquiredImmunity.add(new CatTherapiesReportDTO(cat, therapies));
		});
		
		return new ResponseEntity<>(catsAquiredImmunity, HttpStatus.OK);
	}
	
	@GetMapping("/possibleChronicDisease")
	public ResponseEntity<List<CatTherapiesReportDTO>> getCatsWithPossibleChronicDisease(){
		
		Map<String, List<Therapy>> aquiredImmunityReport = therapyService.possibleChronicDiseaseReport();
		List<CatTherapiesReportDTO> catsAquiredImmunity = new ArrayList<>();
		
		aquiredImmunityReport.forEach((catName, therapies) -> {
			Cat cat = aquiredImmunityReport.get(catName).get(0).getCat();
			catsAquiredImmunity.add(new CatTherapiesReportDTO(cat, therapies));
		});
		
		return new ResponseEntity<>(catsAquiredImmunity, HttpStatus.OK);
	}
	
	@GetMapping("/catBreed")
	public ResponseEntity<List<ReportBreed>> getCatBreedAvgOccuranceInTherapies(){
		List<ReportBreed> breeds = therapyService.catBreedReport();
		return new ResponseEntity<>(breeds, HttpStatus.OK);
	}
}
