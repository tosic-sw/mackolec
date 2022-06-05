package com.vet.mackolec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vet.mackolec.models.Disease;
import com.vet.mackolec.models.ObservedSymptom;
import com.vet.mackolec.models.Symptom;
import com.vet.mackolec.models.Therapy;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DiseaseDeterminationTests {

	private static KieContainer kieContainer;
	  
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
    }
	
	@Test
	 public void diseaseDetermination() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("disease_determination").setFocus();
		
		Symptom symptom1 = new Symptom("slabost");
		Symptom symptom2 = new Symptom("gubitak apetita");
		Symptom symptom3 = new Symptom("nemir");
		Symptom symptom4 = new Symptom("ubrzano disanje");
		
		Symptom symptom5 = new Symptom("sim5");
		Symptom symptom6 = new Symptom("sim6");
		
		
		List<ObservedSymptom> observedSymptoms = new ArrayList<ObservedSymptom>();
		observedSymptoms.add(new ObservedSymptom(symptom1, new Double(5.0)));
		observedSymptoms.add(new ObservedSymptom(symptom2, new Double(4.0)));
		observedSymptoms.add(new ObservedSymptom(symptom3, new Double(4.0)));
		observedSymptoms.add(new ObservedSymptom(symptom4, new Double(3.0)));
		
		// D1
		Set<Symptom> symptomSet1 = new HashSet<Symptom>();
		symptomSet1.add(symptom1);
		symptomSet1.add(symptom2);
		symptomSet1.add(symptom3);
		symptomSet1.add(symptom4);

		Disease disease1 = new Disease();
	    disease1.setName("Bolest 1");
	    disease1.setSymptoms(symptomSet1);
	    
	    // D2
		Set<Symptom> symptomSet2 = new HashSet<Symptom>();
		symptomSet2.add(symptom1);
		symptomSet2.add(symptom2);
		symptomSet2.add(symptom3);
		symptomSet2.add(symptom4);
		symptomSet2.add(symptom5);
		symptomSet2.add(symptom6);
		
		Disease disease2 = new Disease();
	    disease2.setName("Bolest 2");
	    disease2.setSymptoms(symptomSet2);
	    
	    // D3
		Set<Symptom> symptomSet3 = new HashSet<Symptom>();
		symptomSet3.add(symptom1);
		symptomSet3.add(symptom2);
		symptomSet3.add(symptom3);
		
	    Disease disease3 = new Disease();
	    disease3.setName("Bolest 3");
	    disease3.setSymptoms(symptomSet3);
	    
	    
	    Therapy therapy = new Therapy();
	    Set<ObservedSymptom> observedSymptomsSet = new HashSet<ObservedSymptom>();
	    observedSymptoms.forEach(oSymptom -> observedSymptomsSet.add(oSymptom));
		therapy.setObservedSymptoms(observedSymptomsSet);
		
	    
	    kieSession.insert(therapy);
	    kieSession.insert(disease1);
	    kieSession.insert(disease2);
	    kieSession.insert(disease3);

	    kieSession.fireAllRules();
	    
	    assertEquals("Bolest 1", therapy.getDisease().getName());
	    }

	

}
