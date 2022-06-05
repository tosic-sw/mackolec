package com.vet.mackolec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Disease;
import com.vet.mackolec.models.Medicine;
import com.vet.mackolec.models.ObservedSymptom;
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

public class TherapyDeterminationTests {

	private static KieContainer kieContainer;
	  
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
    }
	
	 @Test
	 public void therapyDetermination_MG100() {
		KieBase kieBase = kieContainer.getKieBase("default");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(2), new Integer(300), Breed.RUSKA, CatAge.MACE, Gender.MALE);
		Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), new Disease(),  new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
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
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
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
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
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
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
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
	    
		Therapy therapy = new Therapy(TherapyStrength.UNDEFINED, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), new Disease(), m1);
		
		kieSession.insert(therapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG500, therapy.getTherapyStrength());
	 }
	 
	 @Test
	 public void strongerTherapyMG500() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination_with_history").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(9), new Integer(1800), Breed.RUSKA, CatAge.MLADA_MACKA, Gender.MALE);
		Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    Disease disease = new Disease("Mackomor", new HashSet<Therapy>(), new HashSet<Medicine>());

		Therapy therapy = new Therapy(TherapyStrength.MG300, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		Therapy lastTherapy = new Therapy(TherapyStrength.MG400, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		lastTherapy.setId(1L);
		
		kieSession.insert(therapy);
		kieSession.insert(lastTherapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG500, therapy.getTherapyStrength());
	 }
	 
	 @Test
	 public void strongerTherapyMG200() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination_with_history").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(9), new Integer(1800), Breed.RUSKA, CatAge.MLADA_MACKA, Gender.MALE);
		Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    Disease disease = new Disease("Mackomor", new HashSet<Therapy>(), new HashSet<Medicine>());

		Therapy therapy = new Therapy(TherapyStrength.MG100, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		Therapy lastTherapy = new Therapy(TherapyStrength.MG100, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		lastTherapy.setId(1L);
		
		kieSession.insert(therapy);
		kieSession.insert(lastTherapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(TherapyStrength.MG200, therapy.getTherapyStrength());
	 }
	 
	 @Test
	 public void newMedicineSlabToSrednji() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination_with_history").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(9), new Integer(1800), Breed.RUSKA, CatAge.MLADA_MACKA, Gender.MALE);
		Disease disease = new Disease();
	    disease = new Disease("Mackomor", new HashSet<Therapy>(), createMedicine(disease));
	    Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.SLAB_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.MG500, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		Therapy lastTherapy = new Therapy(TherapyStrength.MG500, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		lastTherapy.setId(1L);
		
		kieSession.insert(therapy);
		kieSession.insert(lastTherapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(MedicineCategory.SREDNJE_JAK_LEK, therapy.getMedicine().getCategory());
	 }
	 
	 @Test
	 public void newMedicineSlabToSrednji2() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination_with_history").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(9), new Integer(1800), Breed.RUSKA, CatAge.MLADA_MACKA, Gender.MALE);
		Disease disease = new Disease();
	    disease = new Disease("Mackomor", new HashSet<Therapy>(), createMedicine(disease));
	    Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.SLAB_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.MG400, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		Therapy lastTherapy = new Therapy(TherapyStrength.MG500, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		lastTherapy.setId(1L);
		
		kieSession.insert(therapy);
		kieSession.insert(lastTherapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(MedicineCategory.SREDNJE_JAK_LEK, therapy.getMedicine().getCategory());
	 }
	 
	 @Test
	 public void newMedicineSrednjiToJak() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination_with_history").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(9), new Integer(1800), Breed.RUSKA, CatAge.MLADA_MACKA, Gender.MALE);
		Disease disease = new Disease();
	    disease = new Disease("Mackomor", new HashSet<Therapy>(), createMedicine(disease));
	    Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.SREDNJE_JAK_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.MG500, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		Therapy lastTherapy = new Therapy(TherapyStrength.MG500, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		lastTherapy.setId(1L);
		
		kieSession.insert(therapy);
		kieSession.insert(lastTherapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(MedicineCategory.JAK_LEK, therapy.getMedicine().getCategory());
	 }
	 
	 @Test
	 public void jakToHospitalization() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("therapy_determination_with_history").setFocus();
		
		Cat cat = new Cat("1241", "Pipiripi", new Integer(9), new Integer(1800), Breed.RUSKA, CatAge.MLADA_MACKA, Gender.MALE);
		Disease disease = new Disease();
	    disease = new Disease("Mackomor", new HashSet<Therapy>(), createMedicine(disease));
	    Medicine m1 = new Medicine("mackolecin", true, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), new Disease(), new HashSet<CatAge>(), new HashSet<Breed>());
	    
		Therapy therapy = new Therapy(TherapyStrength.MG500, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		Therapy lastTherapy = new Therapy(TherapyStrength.MG500, (new Date()).getTime(), Hospitalization.UNDEFINED, cat, new HashSet<ObservedSymptom>(), disease, m1);
		lastTherapy.setId(1L);
		
		kieSession.insert(therapy);
		kieSession.insert(lastTherapy);
	    kieSession.fireAllRules();
	    
	    assertEquals(Hospitalization.NEED, therapy.getHospitalization());
	 }
	 
	 
	 private Set<Medicine> createMedicine(Disease disease) {
		 Set<Medicine> medicine = new HashSet<Medicine>();
		 
		 Medicine m1 = new Medicine("med1", true, false, MedicineCategory.SLAB_LEK, new HashSet<Therapy>(), disease, new HashSet<CatAge>(), new HashSet<Breed>());
		 Medicine m2 = new Medicine("med2", true, false, MedicineCategory.SREDNJE_JAK_LEK, new HashSet<Therapy>(), disease, new HashSet<CatAge>(), new HashSet<Breed>());
		 Medicine m3 = new Medicine("med3", false, true, MedicineCategory.SLAB_LEK, new HashSet<Therapy>(), disease, new HashSet<CatAge>(), new HashSet<Breed>());
		 Medicine m4 = new Medicine("med4", false, true, MedicineCategory.SLAB_LEK, new HashSet<Therapy>(), disease, new HashSet<CatAge>(), new HashSet<Breed>());
		 Medicine m5 = new Medicine("med5", true, false, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, new HashSet<CatAge>(), new HashSet<Breed>());
		 
		 medicine.add(m1);
		 medicine.add(m2);
		 medicine.add(m3);
		 medicine.add(m4);
		 medicine.add(m5);
		 
		 return medicine;
	 }
	 
	 
	 
	 
	 
	 
}
