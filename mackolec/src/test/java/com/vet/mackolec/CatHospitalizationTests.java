package com.vet.mackolec.dummyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.ObservedSymptom;
import com.vet.mackolec.models.Symptom;
import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.Gender;
import com.vet.mackolec.models.enums.Hospitalization;
import com.vet.mackolec.models.helper.CatSymptomsObservation;

import org.junit.Before;
import org.junit.Test;
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
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("hospitalization").setFocus();
		
		Cat cat = new Cat("1241", "Keti", new Integer(4), new Integer(300), Breed.RUSKA, CatAge.MLADA_MACKA, Gender.MALE);
		
		// need to make symptoms with intensity
		Symptom symptom1 = new Symptom("slabost");
		Symptom symptom2 = new Symptom("gubitak apetita");
		Symptom symptom3 = new Symptom("nemir");
		Symptom symptom4 = new Symptom("ubrzano disanje");
		
		List<ObservedSymptom> observedSymptoms = new ArrayList<ObservedSymptom>();
		observedSymptoms.add(new ObservedSymptom(symptom1, new Double(5.0)));
		observedSymptoms.add(new ObservedSymptom(symptom2, new Double(4.0)));
		observedSymptoms.add(new ObservedSymptom(symptom3, new Double(4.0)));
		observedSymptoms.add(new ObservedSymptom(symptom4, new Double(3.0)));
	    
		CatSymptomsObservation catSymptomsObservation = new CatSymptomsObservation(cat, observedSymptoms, Hospitalization.UNDEFINED);
		
		kieSession.insert(catSymptomsObservation);
	    kieSession.fireAllRules();
	    
	    assertEquals(Hospitalization.NEED, catSymptomsObservation.getHospitalization());
	 }
	 
	 @Test
	 public void regularCatHospitalization_NO_NEED() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("hospitalization").setFocus();
		
		Cat cat = new Cat("1242", "Kiti", new Integer(2), new Integer(300), Breed.RUSKA, CatAge.MACE, Gender.MALE);
		
		// need to make symptoms with intensity
		Symptom symptom1 = new Symptom("slabost");
		Symptom symptom2 = new Symptom("gubitak apetita");
		Symptom symptom3 = new Symptom("nemir");
		Symptom symptom4 = new Symptom("ubrzano disanje");
		
		List<ObservedSymptom> observedSymptoms = new ArrayList<ObservedSymptom>();
		observedSymptoms.add(new ObservedSymptom(symptom1, new Double(2.0)));
		observedSymptoms.add(new ObservedSymptom(symptom2, new Double(3.0)));
		observedSymptoms.add(new ObservedSymptom(symptom3, new Double(1.0)));
		observedSymptoms.add(new ObservedSymptom(symptom4, new Double(3.0)));
	    
		CatSymptomsObservation catSymptomsObservation = new CatSymptomsObservation(cat, observedSymptoms, Hospitalization.UNDEFINED);
		
		kieSession.insert(catSymptomsObservation);
	    kieSession.fireAllRules();
	    
	    assertEquals(Hospitalization.NO_NEED, catSymptomsObservation.getHospitalization());
	 }
	 
	 @Test
	 public void badCatHospitalization_nullSympthoms() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("hospitalization").setFocus();
		
		Cat cat = new Cat("1243", "Pipi", new Integer(2), new Integer(300), Breed.RUSKA, CatAge.MACE, Gender.MALE);
	    
		CatSymptomsObservation catSymptomsObservation = new CatSymptomsObservation(cat, null, Hospitalization.UNDEFINED);
		
		kieSession.insert(catSymptomsObservation);
	    kieSession.fireAllRules();
	    
	    assertEquals(Hospitalization.UNDEFINED, catSymptomsObservation.getHospitalization());
	 }
	 
	 @Test
	 public void badCatHospitalization_emptySympthoms() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("hospitalization").setFocus();
		
		Cat cat = new Cat("1244", "Didi", new Integer(2), new Integer(300), Breed.RUSKA, CatAge.MACE, Gender.MALE);
	    
		CatSymptomsObservation catSymptomsObservation = new CatSymptomsObservation(cat, new ArrayList<ObservedSymptom>(), Hospitalization.UNDEFINED);
		
		kieSession.insert(catSymptomsObservation);
	    kieSession.fireAllRules();
	    
	    assertEquals(Hospitalization.UNDEFINED, catSymptomsObservation.getHospitalization());
	 }
}
