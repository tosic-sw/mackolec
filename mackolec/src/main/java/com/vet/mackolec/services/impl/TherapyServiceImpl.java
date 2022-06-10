package com.vet.mackolec.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Disease;
import com.vet.mackolec.models.Medicine;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.MedicineCategory;
import com.vet.mackolec.models.helper.ReportBreed;
import com.vet.mackolec.repositories.TherapyRepository;
import com.vet.mackolec.services.CatService;
import com.vet.mackolec.services.DiseaseService;
import com.vet.mackolec.services.MedicineService;
import com.vet.mackolec.services.TherapyService;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TherapyServiceImpl implements TherapyService {

	private static Long TWELVE_MONTHS = 31556926000L;
	
	private static Long SIX_MONTHS = 15778463000L;
	
	private static Long ONE_MONTH = 2629743830L;
	
	@Autowired
	private TherapyRepository therapyRepository;
	
	@Autowired
	private CatService catService;
	
	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	private KieContainer kieContainer;
	
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

	@Override
	public Map<String, List<Therapy>> aquiredImmunityReport() {
		
		// config session
		KieBase kieBase = kieContainer.getKieBase("default");
		KieSession kieSession = kieBase.newKieSession();
		
		// insert cats and therapies into kieSession
		insertCatsTherapiesInto(kieSession);
		
		//insert medicine into kieSession
		insertMedicineInto(kieSession);	
		
		return exceuteReportQuery12(kieSession, "cats_acquired_immunity_to_the_drug", (new Date()).getTime() - TWELVE_MONTHS);
	}

	@Override
	public Map<String, List<Therapy>> riskOfOrganDamageReport(MedicineCategory medicineCategory) {
		// config session
		KieBase kieBase = kieContainer.getKieBase("default");
		KieSession kieSession = kieBase.newKieSession();
		
		// insert cats and therapies into kieSession
		insertCatsTherapiesInto(kieSession);
		
		//insert medicine into kieSession
		insertMedicineInto(kieSession);
		
		Long date = null;
		if(medicineCategory.equals(MedicineCategory.SREDNJE_JAK_LEK))
			date = (new Date()).getTime() - SIX_MONTHS;
		else
			date = (new Date()).getTime() - SIX_MONTHS/2;
		
		return exceuteReportQuery12(kieSession, "cats_at_risk_of_organ_damage_" + medicineCategory.toString(), date);
	}

	@Override
	public Map<String, List<Therapy>> possibleChronicDiseaseReport() {
		// config session
		KieBase kieBase = kieContainer.getKieBase("default");
		KieSession kieSession = kieBase.newKieSession();
		
		// insert cats and therapies into kieSession
		insertCatsTherapiesInto(kieSession);
		
		//insert disease into kieSession
		insertDiseaseInto(kieSession);
		
		return exceuteReportQuery12(kieSession, "cats_with_possible_chronic_diseases", (new Date()).getTime() - 2*TWELVE_MONTHS);
	}

	@Override
	public List<ReportBreed> catBreedReport() {
		// config session
		KieBase kieBase = kieContainer.getKieBase("default");
		KieSession kieSession = kieBase.newKieSession();
		
		// insert therapies into kieSession
		insertTherapiesInto(kieSession);
		
		List<ReportBreed> breeds = createReportBreeds();
	    
	    for (ReportBreed reportBreed : breeds) {
	    	QueryResults results = kieSession.getQueryResults("cat_breed_avg_occ_in_therapies", reportBreed.getBreedName());
	    	for (QueryResultsRow qResult : results) {
	    		reportBreed.setAvgOccurance((Integer) qResult.get("$value"));
	    	}
	    }
	    
	    return breeds;
	}

	private void insertCatsTherapiesInto(KieSession kieSession) {
		// insert all cats
		List<Cat> cats = catService.findAll();
		for (Cat cat : cats) {
			kieSession.insert(cat);
		}
		
		// insert all therapies
		List<Therapy> therapies = therapyRepository.findAll();
		for (Therapy therapy : therapies) {
			kieSession.insert(therapy);
		}
	}
	
	private void insertMedicineInto(KieSession kieSession) {
		// insert all medicine
		List<Medicine> medicines = medicineService.findAll();
		for (Medicine medicine : medicines) {
			kieSession.insert(medicine);
		}
	}
	
	private void insertDiseaseInto(KieSession kieSession) {
		// insert all diseases
		List<Disease> diseases = diseaseService.findAll();
		for (Disease disease : diseases) {
			kieSession.insert(disease);
		}
	}
	
	private void insertTherapiesInto(KieSession kieSession) {
		// insert all therapies
		List<Therapy> therapies = therapyRepository.findAll();
		for (Therapy therapy : therapies) {
			kieSession.insert(therapy);
		}
	}
	
	private Map<String, List<Therapy>> exceuteReportQuery12(KieSession kieSession, String queryName, Long dateTrashold) {
		QueryResults results = kieSession.getQueryResults(queryName, dateTrashold);
		
		Map<String, List<Therapy>> aquiredImmunityReport = new HashMap<>();
		
		for (QueryResultsRow qResult : results) {
			// for every cat
			List<Therapy> catTherapies = (List<Therapy>) qResult.get("$therapies");
			String catName = catTherapies.get(0).getCat().getName();
			aquiredImmunityReport.put(catName, catTherapies);
		}
		
		return aquiredImmunityReport;
	}

	private List<ReportBreed> createReportBreeds() {
		List<ReportBreed> breeds = new ArrayList<ReportBreed>();
	    
		breeds.add(new ReportBreed(Breed.RUSKA.name(), 0));
	    breeds.add(new ReportBreed(Breed.SIJAMSKA.name(), 0));
	    breeds.add(new ReportBreed(Breed.SIBIRSKA.name(), 0));
	    breeds.add(new ReportBreed(Breed.MAINE_COON.name(), 0));
	    breeds.add(new ReportBreed(Breed.SKOTSKA.name(), 0));
	    breeds.add(new ReportBreed(Breed.SFINKS.name(), 0));
	    
	    return breeds;
	}
}
