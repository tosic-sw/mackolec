package com.vet.mackolec.services.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Disease;
import com.vet.mackolec.models.HospitalizedCat;
import com.vet.mackolec.models.ObservedSymptom;
import com.vet.mackolec.models.Symptom;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.dto.ObservedSymptomDTO;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.Hospitalization;
import com.vet.mackolec.models.enums.TherapyStrength;
import com.vet.mackolec.services.CatService;
import com.vet.mackolec.services.DiseaseService;
import com.vet.mackolec.services.HospitalizedCatService;
import com.vet.mackolec.services.ObservationService;
import com.vet.mackolec.services.SymptomService;
import com.vet.mackolec.services.TherapyService;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservationServiceImpl implements ObservationService {

	@Autowired
	private SymptomService symptomService;
	
	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	private TherapyService therapyService;
	
	@Autowired
	private HospitalizedCatService hospitalizedCatService;
	
	@Autowired
	private CatService catService;
	
	@Autowired
	private KieContainer kieContainer;
	
	@Override
	public Therapy observeCat(Cat cat, List<ObservedSymptomDTO> observedSymptomsDTO) {
		
		Set<ObservedSymptom> observedSymptoms = new HashSet<ObservedSymptom>();
		for (ObservedSymptomDTO observedSymtpom : observedSymptomsDTO) {
			Symptom symptom = symptomService.findOne(observedSymtpom.getName());
			observedSymptoms.add(new ObservedSymptom(symptom, (double) observedSymtpom.getIntensity()));
		}
		
		// config session
		KieBase kieBase = kieContainer.getKieBase("default");
		KieSession kieSession = kieBase.newKieSession();
		
		// new therapy
		Therapy therapy = new Therapy();
		therapy.setObservedSymptoms(observedSymptoms);
		therapy.setHospitalization(Hospitalization.UNDEFINED);
		therapy.setTherapyStrength(TherapyStrength.UNDEFINED);
		therapy.setDate(new Date().getTime());

		
		cat = catService.save(cat);
		cat.setAgeEnum(CatAge.UNDEFINED);
		therapy.setCat(cat); // dodajemo macku u terapiju jer koristimo neke podatke
		
		therapy.setCurrentAge(cat.getAge());
		therapy.setCurrentWeight(cat.getWeight());
		
		List<Disease> diseases = diseaseService.getDiseasesWithSymptomsAndMedicines();
		for (Disease disease : diseases) {
			kieSession.insert(disease);
		}
		
		kieSession.insert(cat);	
		kieSession.insert(therapy);
		
		// -- pravilo za transformaciju godina macke
		runRule(kieSession, "basic_transformation");
	    
		// -- pravilo za odredjivanje bolesti
		runRule(kieSession, "disease_determination");
		
	    // -- pravilo za odredjivanje leka
		runRule(kieSession, "medicine_determination");
		
		// -- pravilo za odredjivanje jacine terapije
		runRule(kieSession, "therapy_determination");
		
		Therapy lastTherapy = therapyService.getLastTherapyOfCat(cat.getJmbm());
		
		if (lastTherapy != null) {

			// -- pravilo za odredjivanje jacine terapije ukoliko je macka dobila istu terapiju
			kieSession.insert(lastTherapy);
			runRule(kieSession, "therapy_determination_with_history");
			
		}
		
		Hospitalization hospBasedOnTherapy = therapy.getHospitalization();
		
		// -- ubaciti pravilo za hospitalizaciju na osnovu simptoma
		runRule(kieSession, "hospitalization");
		
		Hospitalization hospBasedOnSymptoms = therapy.getHospitalization();
		
		if(hospBasedOnTherapy == Hospitalization.NEED || hospBasedOnSymptoms == Hospitalization.NEED) {
			therapy.setHospitalization(Hospitalization.NEED);
			// treba da se sacuva macka
			HospitalizedCat hospCat = new HospitalizedCat((new Date()).getTime(), cat);
			hospitalizedCatService.save(hospCat);
		}
		
		therapyService.save(therapy);
		return therapy;
	}
	
	private void runRule(KieSession kieSession, String agenda) {
		kieSession.getAgenda().getAgendaGroup(agenda).setFocus();		
	    
		kieSession.fireAllRules();
	}
}
