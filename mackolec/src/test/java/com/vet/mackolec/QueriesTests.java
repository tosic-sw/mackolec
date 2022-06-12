package com.vet.mackolec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Disease;
import com.vet.mackolec.models.Medicine;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.Gender;
import com.vet.mackolec.models.enums.MedicineCategory;
import com.vet.mackolec.models.enums.TherapyStrength;
import com.vet.mackolec.models.helper.ReportBreed;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

public class QueriesTests {

	private static Long TWELVE_MONTHS = 31556926000L;
	
	private static Long SIX_MONTHS = 15778463000L;
	
	private static Long ONE_MONTH = 2629743830L;
	
	private static KieContainer kieContainer;
	  
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
		kieContainer = kieServices.newKieClasspathContainer();
    }
	
	@Test
	public void acquiredImmunityToTheDrug() {
	
		KieBase kieBase = kieContainer.getKieBase("query");
		KieSession kieSession = kieBase.newKieSession();
		
		Cat cat1 = createCat("123");
		Cat cat2 = createCat("124");
		
		
		Disease disease = new Disease();
		
		Medicine m1 = new Medicine("med1", true, false, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMaceAndMlada(), getUnsuitableForSkotska());
		m1.setId(1L);
		Medicine m2 = new Medicine("med2", true, false, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMace(), getUnsuitableForRuskaAndSijamska());
		m2.setId(2L);
		Medicine m3 = new Medicine("med3", false, true, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForOdraslaAndStara(), getUnsuitableForRuska());
		m3.setId(3L);
		
		Therapy therapy1 = new Therapy();
		therapy1.setDate((new Date()).getTime() - SIX_MONTHS + ONE_MONTH);
		
		Therapy therapy2 = new Therapy();
		therapy2.setDate((new Date()).getTime() - SIX_MONTHS + 2*ONE_MONTH);
		
		Therapy therapy3 = new Therapy();
		therapy3.setDate((new Date()).getTime() - SIX_MONTHS + 3*ONE_MONTH);
		Therapy therapy4 = new Therapy();
		therapy4.setDate((new Date()).getTime() - 2*SIX_MONTHS - 2*ONE_MONTH);
		
		Therapy therapy5 = new Therapy();
		therapy5.setDate((new Date()).getTime() - SIX_MONTHS + ONE_MONTH);
		
		Therapy therapy6 = new Therapy();
		therapy6.setDate((new Date()).getTime() - SIX_MONTHS + 4*ONE_MONTH);
		
		setCatAndMedicineToTherapy(therapy1, cat1, m1);
		setCatAndMedicineToTherapy(therapy2, cat1, m1);
		setCatAndMedicineToTherapy(therapy3, cat1, m1);
		setCatAndMedicineToTherapy(therapy4, cat1, m1);
		setCatAndMedicineToTherapy(therapy5, cat2, m2);
		setCatAndMedicineToTherapy(therapy6, cat1, m1);
		
		kieSession.insert(cat1);
		kieSession.insert(cat2);
		kieSession.insert(m1);
		kieSession.insert(m2);
		kieSession.insert(m3);
		kieSession.insert(updateTherapy(therapy1));
		kieSession.insert(updateTherapy(therapy2));
		kieSession.insert(updateTherapy(therapy3));
		kieSession.insert(updateTherapy(therapy4));
		kieSession.insert(updateTherapy(therapy5));
		kieSession.insert(updateTherapy(therapy6));
		
		
		QueryResults results = kieSession.getQueryResults("cats_acquired_immunity_to_the_drug", (new Date()).getTime() - TWELVE_MONTHS);
		System.out.println(results.size());
		
		assertEquals(1, results.size());
		
		List<Therapy> therapiesFound = new ArrayList<Therapy>();
		
		for (QueryResultsRow qResult : results) {
			therapiesFound = (List<Therapy>) qResult.get("$therapies");
			for (Therapy therapyRes : therapiesFound) {
				System.out.println(therapyRes);
			}
		}
		
		
		assertEquals(4, therapiesFound.size());
	 
	}
	
	private Therapy updateTherapy(Therapy therapy) {
		Disease d1 = new Disease();
	    d1.setName("a");
	    therapy.setDisease(d1);
	    return therapy;
	}
	

	@Test
	public void catsAtRiskOfOrganDamageSREDNJE() {
	
		KieBase kieBase = kieContainer.getKieBase("query");
		KieSession kieSession = kieBase.newKieSession();
		
//		KieSession kieSession = kieContainer.newKieSession("queries-session");
		
	    Cat cat1 = createCat("123");
	    Cat cat2 = createCat("124");
	    

	    Disease disease = new Disease();
	    disease.setName("Upala mokracnih kanala");
	    
	    Medicine m1 = new Medicine("med1", true, false, MedicineCategory.SREDNJE_JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMaceAndMlada(), getUnsuitableForSkotska());
		m1.setId(1L);
	    Medicine m2 = new Medicine("med2", true, false, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMace(), getUnsuitableForRuskaAndSijamska());
	    m2.setId(2L);
	    Medicine m3 = new Medicine("med3", false, true, MedicineCategory.SLAB_LEK, new HashSet<Therapy>(), disease, getUnsuitableForOdraslaAndStara(), getUnsuitableForRuska());
	    m3.setId(3L);
	    
	    Therapy therapy1 = new Therapy();
	    therapy1.setDate((new Date()).getTime() - ONE_MONTH);
	    therapy1.setTherapyStrength(TherapyStrength.MG400);
	    Therapy therapy2 = new Therapy();
	    therapy2.setDate((new Date()).getTime() - 2*ONE_MONTH);
	    therapy2.setTherapyStrength(TherapyStrength.MG400);
	    Therapy therapy3 = new Therapy();
	    therapy3.setDate((new Date()).getTime() - 3*ONE_MONTH);
	    therapy3.setTherapyStrength(TherapyStrength.MG400);
	    Therapy therapy4 = new Therapy();
	    therapy4.setDate((new Date()).getTime() - 2*SIX_MONTHS);
	    therapy4.setTherapyStrength(TherapyStrength.MG400);
	    Therapy therapy5 = new Therapy();
	    therapy5.setDate((new Date()).getTime() - SIX_MONTHS - ONE_MONTH);
	    therapy5.setTherapyStrength(TherapyStrength.MG400);
		
	    Therapy therapy6 = new Therapy();
	    therapy6.setDate((new Date()).getTime() - 4*ONE_MONTH);
	    therapy6 .setTherapyStrength(TherapyStrength.MG400);
	    

	    Therapy therapy7 = new Therapy();
	    therapy7.setDate((new Date()).getTime() - 4*ONE_MONTH);
	    therapy7.setTherapyStrength(TherapyStrength.MG400);
	    

	    Therapy therapy8 = new Therapy();
	    therapy8.setDate((new Date()).getTime() - 4*ONE_MONTH);
	    therapy8.setTherapyStrength(TherapyStrength.MG400);
	    

	    Therapy therapy9 = new Therapy();
	    therapy9.setDate((new Date()).getTime() - 4*ONE_MONTH);
	    therapy9.setTherapyStrength(TherapyStrength.MG400);
	    
	    setCatAndMedicineToTherapy(therapy1, cat1, m1);
	    setCatAndMedicineToTherapy(therapy2, cat1, m1);
	    setCatAndMedicineToTherapy(therapy3, cat1, m2);
	    setCatAndMedicineToTherapy(therapy4, cat1, m2);
	    setCatAndMedicineToTherapy(therapy5, cat2, m2);
	    setCatAndMedicineToTherapy(therapy6, cat1, m1);
	    setCatAndMedicineToTherapy(therapy7, cat1, m1);
	    setCatAndMedicineToTherapy(therapy8, cat1, m1);
	    setCatAndMedicineToTherapy(therapy9, cat1, m1);
	    
	    kieSession.insert(cat1);
	    kieSession.insert(cat2);
	    kieSession.insert(m1);
	    kieSession.insert(m2);
	    kieSession.insert(m3);
	    kieSession.insert(updateTherapy(therapy1));
	    kieSession.insert(updateTherapy(therapy2));
	    kieSession.insert(updateTherapy(therapy3));
	    kieSession.insert(updateTherapy(therapy4));
	    kieSession.insert(updateTherapy(therapy5));
	    kieSession.insert(updateTherapy(therapy6));
	    kieSession.insert(updateTherapy(therapy7));
	    kieSession.insert(updateTherapy(therapy8));
	    kieSession.insert(updateTherapy(therapy9));
	    
	    
	    QueryResults results = kieSession.getQueryResults("cats_at_risk_of_organ_damage_SREDNJE_JAK_LEK", (new Date()).getTime() - SIX_MONTHS);
	    System.out.println(results.size());
	    
	    assertEquals(1, results.size());
	    
	    List<Therapy> therapiesFound = new ArrayList<Therapy>();
	    
	    for (QueryResultsRow qResult : results) {
	    	therapiesFound = (List<Therapy>) qResult.get("$therapies");
	    	for (Therapy therapyRes : therapiesFound) {
	    		System.out.println(therapyRes);
	    	}
	    }
	    
	    
	    assertEquals(6, therapiesFound.size());
	 
	}
	
	@Test
	public void catsAtRiskOfOrganDamageJAK() {
	
		KieBase kieBase = kieContainer.getKieBase("query");
		KieSession kieSession = kieBase.newKieSession();
		
	    Cat cat1 = createCat("123");
	    Cat cat2 = createCat("124");
	    

	    Disease disease = new Disease();
	    
	    Medicine m1 = new Medicine("med1", true, false, MedicineCategory.JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMaceAndMlada(), getUnsuitableForSkotska());
		m1.setId(1L);
	    Medicine m2 = new Medicine("med2", true, false, MedicineCategory.SREDNJE_JAK_LEK, new HashSet<Therapy>(), disease, getUnsuitableForMace(), getUnsuitableForRuskaAndSijamska());
	    m2.setId(2L);
	    Medicine m3 = new Medicine("med3", false, true, MedicineCategory.SLAB_LEK, new HashSet<Therapy>(), disease, getUnsuitableForOdraslaAndStara(), getUnsuitableForRuska());
	    m3.setId(3L);
	    
		Therapy therapy1 = new Therapy();
	    therapy1.setDate((new Date()).getTime() - ONE_MONTH);
	    therapy1.setTherapyStrength(TherapyStrength.MG400);
	    therapy1.setDisease(disease);
		
	    Therapy therapy2 = new Therapy();
	    therapy2.setDate((new Date()).getTime() - 2*ONE_MONTH);
	    therapy2.setTherapyStrength(TherapyStrength.MG400);
	    therapy2.setDisease(disease);
		
	    Therapy therapy3 = new Therapy();
	    therapy3.setDate((new Date()).getTime() - ONE_MONTH);
	    therapy3.setTherapyStrength(TherapyStrength.MG400);
	    therapy3.setDisease(disease);
		
	    Therapy therapy4 = new Therapy();
	    therapy4.setDate((new Date()).getTime() - 2*SIX_MONTHS);
	    therapy4.setTherapyStrength(TherapyStrength.MG400);
	    therapy4.setDisease(disease);
		
	    Therapy therapy5 = new Therapy();
	    therapy5.setDate((new Date()).getTime() - SIX_MONTHS - ONE_MONTH);
	    therapy5.setTherapyStrength(TherapyStrength.MG400);
	    therapy5.setDisease(disease);
	    
		Therapy therapy6 = new Therapy();
	    therapy6.setDate((new Date()).getTime() - ONE_MONTH);
	    therapy6 .setTherapyStrength(TherapyStrength.MG400);
	    therapy6.setDisease(disease);

		Therapy therapy7 = new Therapy();
	    therapy7.setDate((new Date()).getTime() - 2*ONE_MONTH);
	    therapy7.setTherapyStrength(TherapyStrength.MG400);
	    therapy7.setDisease(disease);

	    Therapy therapy8 = new Therapy();
	    therapy8.setDate((new Date()).getTime() - ONE_MONTH);
	    therapy8.setTherapyStrength(TherapyStrength.MG400);
	    therapy8.setDisease(disease);

	    Therapy therapy9 = new Therapy();
	    therapy9.setDate((new Date()).getTime() - 2*ONE_MONTH);
	    therapy9.setTherapyStrength(TherapyStrength.MG400);
	    therapy9.setDisease(disease);
	    
	    setCatAndMedicineToTherapy(therapy1, cat1, m1);
	    setCatAndMedicineToTherapy(therapy2, cat1, m1);
	    setCatAndMedicineToTherapy(therapy3, cat1, m2);
	    setCatAndMedicineToTherapy(therapy4, cat1, m2);
	    setCatAndMedicineToTherapy(therapy5, cat2, m2);
	    setCatAndMedicineToTherapy(therapy6, cat1, m1);
	    setCatAndMedicineToTherapy(therapy7, cat1, m1);
	    setCatAndMedicineToTherapy(therapy8, cat1, m1);
	    setCatAndMedicineToTherapy(therapy9, cat1, m1);
	    
	    kieSession.insert(cat1);
	    kieSession.insert(cat2);
	    kieSession.insert(m1);
	    kieSession.insert(m2);
	    kieSession.insert(m3);
	    kieSession.insert(therapy1);
	    kieSession.insert(therapy2);
	    kieSession.insert(therapy3);
	    kieSession.insert(therapy4);
	    kieSession.insert(therapy5);
	    kieSession.insert(therapy6);
	    kieSession.insert(therapy7);
	    kieSession.insert(therapy8);
	    kieSession.insert(therapy9);
	    
	    
	    QueryResults results = kieSession.getQueryResults("cats_at_risk_of_organ_damage_JAK_LEK", (new Date()).getTime() - 3*ONE_MONTH);
	    System.out.println(results.size());
	    
	    assertEquals(1, results.size());
	    
	    List<Therapy> therapiesFound = new ArrayList<Therapy>();
	    
	    for (QueryResultsRow qResult : results) {
	    	therapiesFound = (List<Therapy>) qResult.get("$therapies");
	    	for (Therapy therapyRes : therapiesFound) {
	    		System.out.println(therapyRes);
	    	}
	    }
	    
	    assertEquals(6, therapiesFound.size());
	 
	}

	@Test
	public void catsWithPossibleChronicDiseases() {
	
		KieBase kieBase = kieContainer.getKieBase("query");
		KieSession kieSession = kieBase.newKieSession();
		
	    Cat cat1 = createCat("123");
	    Cat cat2 = createCat("124");
	    

	    Disease disease1 = new Disease();
	    disease1.setId(1L);
	    disease1.setName("Upala bubrega");
	    Disease disease2 = new Disease();
	    disease2.setId(2L);
	    disease2.setName("Upala desni");
	    Disease disease3 = new Disease();
	    disease3.setId(3L);
	    disease3.setName("Dijabetes");
	    
	    Therapy therapy1 = new Therapy();
	    therapy1.setDate((new Date()).getTime() - ONE_MONTH);
	    therapy1.setDisease(disease1);
	    therapy1.setCat(cat1);
	    Medicine m = new Medicine();
	    m.setCategory(MedicineCategory.SLAB_LEK);
	    therapy1.setMedicine(m);
	    
	    Therapy therapy2 = new Therapy();
	    therapy2.setDate((new Date()).getTime() - 2*ONE_MONTH);
	    therapy2.setDisease(disease1);
	    therapy2.setCat(cat1);
	    m.setCategory(MedicineCategory.SLAB_LEK);
	    therapy2.setMedicine(m);
	    
	    Therapy therapy3 = new Therapy();
	    therapy3.setDate((new Date()).getTime() - 7*ONE_MONTH);
	    therapy3.setDisease(disease1);
	    therapy3.setCat(cat1);
	    m.setCategory(MedicineCategory.SLAB_LEK);
	    therapy3.setMedicine(m);
	    
	    Therapy therapy4 = new Therapy();
	    therapy4.setDate((new Date()).getTime() - 2*SIX_MONTHS);
	    therapy4.setDisease(disease1);
	    therapy4.setCat(cat1);
	    m.setCategory(MedicineCategory.SLAB_LEK);
	    therapy4.setMedicine(m);
	    
	    Therapy therapy5 = new Therapy();
	    therapy5.setDate((new Date()).getTime() - SIX_MONTHS - ONE_MONTH);
	    therapy5.setDisease(disease2);
	    therapy5.setCat(cat2);
	    m.setCategory(MedicineCategory.SLAB_LEK);
	    therapy5.setMedicine(m);
	    
	    Therapy therapy6 = new Therapy();
	    therapy6.setDate((new Date()).getTime() - ONE_MONTH);
	    therapy6.setDisease(disease3);
	    therapy6.setCat(cat2);
	    m.setCategory(MedicineCategory.SLAB_LEK);
	    therapy6.setMedicine(m);
	    
	    Therapy therapy7 = new Therapy();
	    therapy7.setDate((new Date()).getTime() - 2*ONE_MONTH);
	    therapy7.setDisease(disease3);
	    therapy7.setCat(cat2);
	    m.setCategory(MedicineCategory.SLAB_LEK);
	    therapy7.setMedicine(m);
	    
	    
	    
	    kieSession.insert(cat1);
	    kieSession.insert(cat2);
	    kieSession.insert(disease1);
	    kieSession.insert(disease2);
	    kieSession.insert(disease3);
	    kieSession.insert(therapy1);
	    kieSession.insert(therapy2);
	    kieSession.insert(therapy3);
	    kieSession.insert(therapy4);
	    kieSession.insert(therapy5);
	    kieSession.insert(therapy6);
	    kieSession.insert(therapy7);
	    
	    
	    QueryResults results = kieSession.getQueryResults("cats_with_possible_chronic_diseases", (new Date()).getTime() - 2*TWELVE_MONTHS);
	    System.out.println(results.size());
	    
	    assertEquals(1, results.size());
	    
	    List<Therapy> therapiesFound = new ArrayList<Therapy>();
	    
	    for (QueryResultsRow qResult : results) {
	    	therapiesFound = (List<Therapy>) qResult.get("$therapies");
	    	for (Therapy therapyRes : therapiesFound) {
	    		System.out.println(therapyRes);
	    	}
	    }
	    
	    assertEquals(4, therapiesFound.size());
	 
	}
	
	@Test
	public void therapiesPerCatsBreed() {
	
		KieBase kieBase = kieContainer.getKieBase("query");
		KieSession kieSession = kieBase.newKieSession();
		
	    Cat cat1 = createCat("123");
	    cat1.setBreed(Breed.RUSKA);
	    Cat cat2 = createCat("124");
	    cat2.setBreed(Breed.MAINE_COON);
	    Cat cat3 = createCat("125");
	    cat3.setBreed(Breed.SIBIRSKA);
	    Cat cat4 = createCat("126");
	    cat4.setBreed(Breed.SIJAMSKA);
	    Cat cat5 = createCat("127");
	    cat5.setBreed(Breed.SKOTSKA);
		
	    kieSession.insert(cat1);
	    kieSession.insert(cat2);
	    kieSession.insert(cat3);
	    kieSession.insert(cat4);
	    kieSession.insert(cat5);
	    kieSession.insert(createTherapy(cat1));
	    kieSession.insert(createTherapy(cat2));
	    kieSession.insert(createTherapy(cat3));
	    kieSession.insert(createTherapy(cat4));
	    kieSession.insert(createTherapy(cat5));
	    kieSession.insert(createTherapy(cat4));
	    kieSession.insert(createTherapy(cat3));
	    kieSession.insert(createTherapy(cat2));
	    kieSession.insert(createTherapy(cat1));
	    
//	    MostSusceptibleToDiseaseReport mstdr = new MostSusceptibleToDiseaseReport();
	    List<ReportBreed> breeds = new ArrayList<ReportBreed>();
	    breeds.add(new ReportBreed(Breed.RUSKA.name(), 0));
	    breeds.add(new ReportBreed(Breed.SIJAMSKA.name(), 0));
	    breeds.add(new ReportBreed(Breed.SIBIRSKA.name(), 0));
	    breeds.add(new ReportBreed(Breed.MAINE_COON.name(), 0));
	    breeds.add(new ReportBreed(Breed.SKOTSKA.name(), 0));
//	    kieSession.insert(mstdr);
	    
	    for (ReportBreed reportBreed : breeds) {
	    	QueryResults results = kieSession.getQueryResults("cat_breed_avg_occ_in_therapies", reportBreed.getBreedName());
	    	System.out.println("---------------------------------");
	    	System.out.println(reportBreed.getBreedName());
	    	System.out.println("Results size: " + results.size());
	    	for (QueryResultsRow qResult : results) {
	    		Integer numOfOccurances = (Integer) qResult.get("$value");
	    		System.out.println("Num of occurances " + numOfOccurances);
	    	}
	    }
	 
	}

	private Therapy createTherapy(Cat cat) {
		Therapy therapy = new Therapy();
	    therapy.setCat(cat);
	    therapy.setMedicine(new Medicine());
	    Disease d1 = new Disease();
	    d1.setName("a");
	    therapy.setDisease(d1);
	    return therapy;
	}

	private void setCatAndMedicineToTherapy(Therapy therapy, Cat cat, Medicine m) {
		therapy.setCat(cat);
		therapy.setMedicine(m);
		therapy.setTherapyStrength(TherapyStrength.UNDEFINED);
	}

	private Cat createCat(String jmbm) {
		 Cat cat = new Cat();
		 cat.setJmbm(jmbm);
		 cat.setName("");
	     cat.setAge(2);
	     cat.setAgeEnum(CatAge.MACE);
	     cat.setBreed(Breed.RUSKA);
	     cat.setGender(Gender.MALE);
		 
		 return cat;
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
