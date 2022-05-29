package com.vet.mackolec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Disease;
import com.vet.mackolec.models.Medicine;
import com.vet.mackolec.models.ObservedSymptom;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.Gender;
import com.vet.mackolec.models.enums.MedicineCategory;
import com.vet.mackolec.models.enums.TherapyStrength;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class TherapyDeterminationTests {

	private static KieContainer kieContainer;
	  
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
    }
	
	 @Test
	 public void therapyDetermination_MG100() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(2), new Integer(300), Breed.RUSKA, CatAge.MACE, Gender.MALE);
		Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), new Disease(),  new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
		kieSession.insert(therapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG100, therapy.getTherapyStrength());
	 }
	 
	 @Test
	 public void therapyDetermination_MG200() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(25), new Integer(1500), Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.SREDNJE_JAK_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
		kieSession.insert(therapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG300, therapy.getTherapyStrength());
	 }	 
	 
	 @Test
	 public void therapyDetermination_MG300() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(30), new Integer(7100), Breed.MAINE_COON, CatAge.ODRASLA_MACKA, Gender.MALE);
		Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
		kieSession.insert(therapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG400, therapy.getTherapyStrength());
	 }
	 
	 @Test
	 public void therapyDetermination_MG400() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(9), new Integer(1800), Breed.RUSKA, CatAge.MLADA_MACKA, Gender.MALE);
		Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
		kieSession.insert(therapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG200, therapy.getTherapyStrength());
	 }
	 
	 @Test
	 public void therapyDetermination_MG500() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(2), new Integer(7300), Breed.RUSKA, CatAge.MACE, Gender.MALE);
		Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.SREDNJE_JAK_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
		kieSession.insert(therapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG500, therapy.getTherapyStrength());
	 }
}
