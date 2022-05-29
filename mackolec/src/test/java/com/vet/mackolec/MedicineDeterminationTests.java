package com.vet.mackolec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Disease;
import com.vet.mackolec.models.Medicine;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.Gender;
import com.vet.mackolec.models.enums.MedicineCategory;

public class MedicineDeterminationTests {
	
	private static KieContainer kieContainer;
	
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
    }
	
	 @Test
	 public void maceTest() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("medicine_determination").setFocus();
		
	    Cat cat = createCat();
	    Disease disease = new Disease();
	    disease.setMedicine(createMedicine(disease));
	    
	    Therapy therapy = new Therapy();
		therapy.setDisease(disease);
		therapy.setCat(cat);
	    
	    kieSession.insert(therapy);
	    kieSession.fireAllRules();
	    
	    assertEquals("med4", therapy.getMedicine().getName());
	    }
	 
	 private Cat createCat() {
		 Cat cat = new Cat();
		 cat.setName("");
	     cat.setAge(2);
	     cat.setAgeEnum(CatAge.MACE);
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
