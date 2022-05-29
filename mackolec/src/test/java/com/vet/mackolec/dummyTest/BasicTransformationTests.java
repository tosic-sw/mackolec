package com.vet.mackolec.dummyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.enums.CatAge;

public class BasicTransformationTests {
	
	private static KieContainer kieContainer;
	  
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
    }
	
	 @Test
	 public void maceTest() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("basic_transformation").setFocus();
		
	    Cat cat = new Cat();
	    cat.setAge(2);
	    
	    kieSession.insert(cat);
	    kieSession.fireAllRules();
	    
	    assertEquals(cat.getAgeEnum(), CatAge.MACE);
	 }
	 
	 @Test
	 public void mladaMackaTest() {
		 KieSession kieSession = kieContainer.newKieSession();
		 kieSession.getAgenda().getAgendaGroup("basic_transformation").setFocus();
		 
		 Cat cat = new Cat();
		 cat.setAge(4);
		    
		 kieSession.insert(cat);
		 kieSession.fireAllRules();
		    
		 assertEquals(cat.getAgeEnum(), CatAge.MLADA_MACKA);
	 }
	 
	 @Test
	 public void odraslaMackaTest() {
		 KieSession kieSession = kieContainer.newKieSession();
		 kieSession.getAgenda().getAgendaGroup("basic_transformation").setFocus();
		 
		 Cat cat = new Cat();
		 cat.setAge(25);
		    
		 kieSession.insert(cat);
		 kieSession.fireAllRules();
		    
		 assertEquals(cat.getAgeEnum(), CatAge.ODRASLA_MACKA);
	 }
	 
	 @Test
	 public void staraMackaTest() {
		 KieSession kieSession = kieContainer.newKieSession();
		 kieSession.getAgenda().getAgendaGroup("basic_transformation").setFocus();
		 
		 Cat cat = new Cat();
		 cat.setAge(50);
		    
		 kieSession.insert(cat);
		 kieSession.fireAllRules();
		    
		 assertEquals(cat.getAgeEnum(), CatAge.STARA_MACKA);
	 }

}
