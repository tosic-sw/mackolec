package com.vet.mackolec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.ObservedSymptom;
import com.vet.mackolec.models.Symptom;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.Gender;
import com.vet.mackolec.models.enums.Hospitalization;
import com.vet.mackolec.models.helper.CatSymptomsObservation;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class CatHospitalizationTests {

	private static KieContainer kieContainer;
	  
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
    }
	
	 @Test
	 public void regularCatHospitalization_NEED() {
		KieBase kieBase = kieContainer.getKieBase("default");
		KieSession kieSession = kieBase.newKieSession(); 
		kieSession.getAgenda().getAgendaGroup("hospitalization").setFocus();
		
		// need to make symptoms with intensity
		Symptom symptom1 = new Symptom("slabost");
		Symptom symptom2 = new Symptom("gubitak apetita");
		Symptom symptom3 = new Symptom("nemir");
		Symptom symptom4 = new Symptom("ubrzano disanje");
		
		Set<ObservedSymptom> observedSymptoms = new HashSet<ObservedSymptom>();
		observedSymptoms.add(new ObservedSymptom(symptom1, new Double(5.0)));
		observedSymptoms.add(new ObservedSymptom(symptom2, new Double(4.0)));
		observedSymptoms.add(new ObservedSymptom(symptom3, new Double(4.0)));
		observedSymptoms.add(new ObservedSymptom(symptom4, new Double(3.0)));
	    
		Therapy therapy = new Therapy();
		therapy.setObservedSymptoms(observedSymptoms);
		therapy.setHospitalization(Hospitalization.UNDEFINED);
		
	    kieSession.insert(therapy);
		kieSession.fireAllRules();
	    
	    assertEquals(Hospitalization.NEED, therapy.getHospitalization());
	 }
	 
	 @Test
	 public void regularCatHospitalization_NO_NEED() {
		KieBase kieBase = kieContainer.getKieBase("default");
		KieSession kieSession = kieBase.newKieSession(); 
		kieSession.getAgenda().getAgendaGroup("hospitalization").setFocus();
		
		// need to make symptoms with intensity
		Symptom symptom1 = new Symptom("slabost");
		Symptom symptom2 = new Symptom("gubitak apetita");
		Symptom symptom3 = new Symptom("nemir");
		Symptom symptom4 = new Symptom("ubrzano disanje");
		
		Set<ObservedSymptom> observedSymptoms = new HashSet<ObservedSymptom>();
		observedSymptoms.add(new ObservedSymptom(symptom1, new Double(2.0)));
		observedSymptoms.add(new ObservedSymptom(symptom2, new Double(3.0)));
		observedSymptoms.add(new ObservedSymptom(symptom3, new Double(1.0)));
		observedSymptoms.add(new ObservedSymptom(symptom4, new Double(3.0)));
	    
		Therapy therapy = new Therapy();
		therapy.setObservedSymptoms(observedSymptoms);
		therapy.setHospitalization(Hospitalization.UNDEFINED);
		
	    kieSession.insert(therapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(Hospitalization.NO_NEED, therapy.getHospitalization());
	 }
}
