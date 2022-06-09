package com.vet.mackolec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Disease;
import com.vet.mackolec.models.Medicine;
import com.vet.mackolec.models.ObservedSymptom;
import com.vet.mackolec.models.Symptom;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.Gender;
import com.vet.mackolec.models.enums.Hospitalization;
import com.vet.mackolec.models.enums.MedicineCategory;
import com.vet.mackolec.models.enums.TherapyStrength;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ForwardChainTests {
	
	private static KieContainer kieContainer;
	  
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
    }
	
	 @Test
	 public void forwardTest() {
		KieBase kieBase = kieContainer.getKieBase("default");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("basic_transformation").setFocus();
		
		Cat cat = createCat();
		kieSession.insert(cat);
	    kieSession.fireAllRules();
	    
	    assertEquals(cat.getAgeEnum(), CatAge.MACE);
	    
	    // Change agenda ----------------------------------------------------------
	    kieSession.getAgenda().getAgendaGroup("disease_determination").setFocus();
	    
		Symptom symptom1 = new Symptom("slabost");
		Symptom symptom2 = new Symptom("gubitak apetita");
		Symptom symptom3 = new Symptom("nemir");
		Symptom symptom4 = new Symptom("ubrzano disanje");
		Symptom symptom5 = new Symptom("sim5");
		Symptom symptom6 = new Symptom("sim6");
		
		Set<ObservedSymptom> observedSymptoms = new HashSet<ObservedSymptom>();
		observedSymptoms.add(new ObservedSymptom(symptom1, new Double(5.0)));
		observedSymptoms.add(new ObservedSymptom(symptom2, new Double(4.0)));
		observedSymptoms.add(new ObservedSymptom(symptom3, new Double(4.0)));
		observedSymptoms.add(new ObservedSymptom(symptom4, new Double(3.0)));
		
		Therapy therapy = new Therapy();
		therapy.setId(null);
		therapy.setObservedSymptoms(observedSymptoms);
		therapy.setHospitalization(Hospitalization.UNDEFINED);
		therapy.setTherapyStrength(TherapyStrength.UNDEFINED);
		therapy.setDate(new Date().getTime());
		
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
	  
	    kieSession.insert(therapy);
	    kieSession.insert(disease1);
	    kieSession.insert(disease2);
	    kieSession.insert(disease3);
	    kieSession.fireAllRules();
	    
	    assertEquals("Bolest 1", therapy.getDisease().getName());
	    
	    // Change agenda ----------------------------------------------------------
	    kieSession.getAgenda().getAgendaGroup("medicine_determination").setFocus();
	    
	    disease1.setMedicine(createMedicine(disease1));
		therapy.setCat(cat);
	    
		kieSession.fireAllRules();
	    
	    assertEquals("med4", therapy.getMedicine().getName());
	    
	    // Change agenda ----------------------------------------------------------
	    kieSession.getAgenda().getAgendaGroup("therapy_determination").setFocus();
	    
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG100, therapy.getTherapyStrength());
	    
	    // Change agenda ----------------------------------------------------------
	    kieSession.getAgenda().getAgendaGroup("therapy_determination_with_history").setFocus();
	    
	    Therapy lastTherapy = new Therapy(TherapyStrength.MG100, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), therapy.getDisease(), therapy.getMedicine());
		lastTherapy.setId(1L);
		
	    kieSession.insert(lastTherapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG200, therapy.getTherapyStrength());
	 }
	 
	 private Cat createCat() {
		 Cat cat = new Cat();
		 cat.setJmbm("001");
		 cat.setName("Cvetko");
	     cat.setAge(new Integer(2));
	     cat.setWeight(new Integer(400));
	     cat.setAgeEnum(CatAge.UNDEFINED);
	     cat.setBreed(Breed.RUSKA);
	     cat.setGender(Gender.MALE);
		 
		 return cat;
	 }
	 
	 private Set<Medicine> createMedicine(Disease disease) {
		 Set<Medicine> medicine = new HashSet<Medicine>();
		 
		 Medicine m1 = new Medicine("med1", true, false, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMaceAndMlada(), getUnsuitableForSkotska());
		 Medicine m2 = new Medicine("med2", true, false, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMace(), getUnsuitableForRuskaAndSijamska());
		 Medicine m3 = new Medicine("med3", false, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForOdraslaAndStara(), getUnsuitableForRuska());
		 Medicine m4 = new Medicine("med4", false, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMladaAndOdrasla(), getUnsuitableForSkotska());
		 Medicine m5 = new Medicine("med5", true, false, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMladaAndOdrasla(), getUnsuitableForSkotska());
		 
		 medicine.add(m1);
		 medicine.add(m2);
		 medicine.add(m3);
		 medicine.add(m4);
		 medicine.add(m5);
		 
		 return medicine;
	 }
	 
	 private Set<CatAge> getUnsuitableForMaceAndMlada() {
		 Set<CatAge> set = new HashSet<>();
		 set.add(CatAge.MACE);
		 set.add(CatAge.MLADA_MACKA);
		 return set;
	 }
	 
	 private Set<CatAge> getUnsuitableForOdraslaAndStara() {
		 Set<CatAge> set = new HashSet<>();
		 set.add(CatAge.ODRASLA_MACKA);
		 set.add(CatAge.STARA_MACKA);
		 return set;
	 }
	 
	 private Set<CatAge> getUnsuitableForMace() {
		 Set<CatAge> set = new HashSet<>();
		 set.add(CatAge.MACE);
		 return set;
	 }
	 
	 private Set<CatAge> getUnsuitableForMladaAndOdrasla() {
		 Set<CatAge> set = new HashSet<>();
		 set.add(CatAge.MLADA_MACKA);
		 set.add(CatAge.ODRASLA_MACKA);
		 return set;
	 }
	 
	 private Set<Breed> getUnsuitableForRuska() {
		 Set<Breed> set = new HashSet<>();
		 set.add(Breed.RUSKA);
		 return set;
	 }
	 
	 private Set<Breed> getUnsuitableForRuskaAndSijamska() {
		 Set<Breed> set = new HashSet<>();
		 set.add(Breed.RUSKA);
		 set.add(Breed.SIJAMSKA);
		 return set;
	 }
	 
	 private Set<Breed> getUnsuitableForSkotska() {
		 Set<Breed> set = new HashSet<>();
		 set.add(Breed.SKOTSKA);
		 return set;
	 }
	

}
