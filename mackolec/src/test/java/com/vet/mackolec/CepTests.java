package com.vet.mackolec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.vet.mackolec.events.FluidFlowEvent;
import com.vet.mackolec.events.HeartBeatEvent;
import com.vet.mackolec.events.OxygenLevelEvent;
import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.HospitalizedCat;
import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.Gender;
import com.vet.mackolec.models.helper.AlarmNotificationContainer;

public class CepTests {
	
	private static KieContainer kieContainer;
	  
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
    }
	
	@Test
	public void testHeartBeatEvent() {
		// sout 001
		KieBase kieBase = kieContainer.getKieBase("cep");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();

		AlarmNotificationContainer container = new AlarmNotificationContainer();
		Cat cat1 = new Cat("001", "Kica", 5, 7, Breed.MAINE_COON, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat1 = new HospitalizedCat(new Date().getTime(), cat1);
		Cat cat2 = new Cat("002", "Mica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat2 = new HospitalizedCat(new Date().getTime(), cat2);
		
		kieSession.insert(container);
		kieSession.insert(hcat1);
		kieSession.insert(hcat2);
		for(int i = 0; i < 40; i++) {
			kieSession.insert(new HeartBeatEvent(hcat1.getCat().getJmbm()));
			
			if(i%2 == 0)
				kieSession.insert(new HeartBeatEvent(hcat2.getCat().getJmbm()));
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		assertEquals(1, container.getNotifications().size());
	}
	
	@Test
	public void testHeartBeatEvent2() {
		// sout 001, 002
		KieBase kieBase = kieContainer.getKieBase("cep");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();

		AlarmNotificationContainer container = new AlarmNotificationContainer();
		Cat cat1 = new Cat("001", "Kica", 5, 7, Breed.MAINE_COON, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat1 = new HospitalizedCat(new Date().getTime(), cat1);
		Cat cat2 = new Cat("002", "Mica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat2 = new HospitalizedCat(new Date().getTime(), cat2);
		
		kieSession.insert(container);
		kieSession.insert(hcat1);
		kieSession.insert(hcat2);
		for(int i = 0; i < 40; i++) {
			kieSession.insert(new HeartBeatEvent(hcat1.getCat().getJmbm()));
			kieSession.insert(new HeartBeatEvent(hcat2.getCat().getJmbm()));
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		assertEquals(1, container.getNotifications().size());
	}
	
	@Test
	public void testHeartBeatEvent3() {
		// no sout
		KieBase kieBase = kieContainer.getKieBase("cep");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();

		AlarmNotificationContainer container = new AlarmNotificationContainer();
		Cat cat1 = new Cat("001", "Kica", 5, 7, Breed.MAINE_COON, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat1 = new HospitalizedCat(new Date().getTime(), cat1);
		Cat cat2 = new Cat("002", "Mica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat2 = new HospitalizedCat(new Date().getTime(), cat2);
		
		kieSession.insert(container);
		kieSession.insert(hcat1);
		kieSession.insert(hcat2);
		for(int i = 0; i < 30; i++) {
			kieSession.insert(new HeartBeatEvent(hcat1.getCat().getJmbm()));
			kieSession.insert(new HeartBeatEvent(hcat2.getCat().getJmbm()));
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		assertEquals(0, container.getNotifications().size());
	}
	
	@Test
	public void testOxygenLevelEvent1() {
		// sout 002
		KieBase kieBase = kieContainer.getKieBase("cep");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("oxygen_level").setFocus();

		AlarmNotificationContainer container = new AlarmNotificationContainer();
		Cat cat1 = new Cat("001", "Kica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat1 = new HospitalizedCat(new Date().getTime(), cat1);
		Cat cat2 = new Cat("002", "Mica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat2 = new HospitalizedCat(new Date().getTime(), cat2);
		
		kieSession.insert(container);
		kieSession.insert(hcat1);
		kieSession.insert(hcat2);
		for(int i = 0; i < 40; i++) {
			kieSession.insert(new OxygenLevelEvent(hcat1.getCat().getJmbm(), 95.0));
			kieSession.insert(new OxygenLevelEvent(hcat1.getCat().getJmbm(), 93.0));
			kieSession.insert(new OxygenLevelEvent(hcat2.getCat().getJmbm(), 93.0));
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		assertEquals(1, container.getNotifications().size());
	}
	
	@Test
	public void testOxygenLevelEvent2() {
		// No sout
		KieBase kieBase = kieContainer.getKieBase("cep");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("oxygen_level").setFocus();

		AlarmNotificationContainer container = new AlarmNotificationContainer();
		Cat cat1 = new Cat("001", "Kica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat1 = new HospitalizedCat(new Date().getTime(), cat1);
		Cat cat2 = new Cat("002", "Mica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat2 = new HospitalizedCat(new Date().getTime(), cat2);
		
		kieSession.insert(container);
		kieSession.insert(hcat1);
		kieSession.insert(hcat2);
		for(int i = 0; i < 40; i++) {
			kieSession.insert(new OxygenLevelEvent(hcat1.getCat().getJmbm(), 93.0));
			kieSession.insert(new OxygenLevelEvent(hcat1.getCat().getJmbm(), 95.0));
			kieSession.insert(new OxygenLevelEvent(hcat2.getCat().getJmbm(), 93.0));
			kieSession.insert(new OxygenLevelEvent(hcat2.getCat().getJmbm(), 95.0));
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		assertEquals(0, container.getNotifications().size());
	}
	
	@Test
	public void testOxygenLevelEvent3() {
		// sout 001, 002
		KieBase kieBase = kieContainer.getKieBase("cep");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("oxygen_level").setFocus();

		AlarmNotificationContainer container = new AlarmNotificationContainer();
		Cat cat1 = new Cat("001", "Kica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat1 = new HospitalizedCat(new Date().getTime(), cat1);
		Cat cat2 = new Cat("002", "Mica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat2 = new HospitalizedCat(new Date().getTime(), cat2);
		
		kieSession.insert(container);
		kieSession.insert(hcat1);
		kieSession.insert(hcat2);
		for(int i = 0; i < 40; i++) {
			kieSession.insert(new OxygenLevelEvent(hcat1.getCat().getJmbm(), 93.0));
			kieSession.insert(new OxygenLevelEvent(hcat2.getCat().getJmbm(), 93.0));
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		assertEquals(1, container.getNotifications().size());
	}
	
	@Test
	public void testFluidLevelEvent1() {
		// sout 002
		KieBase kieBase = kieContainer.getKieBase("cep");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("fluid_level").setFocus();

		AlarmNotificationContainer container = new AlarmNotificationContainer();
		Cat cat1 = new Cat("001", "Kica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat1 = new HospitalizedCat(new Date().getTime(), cat1);
		Cat cat2 = new Cat("002", "Mica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat2 = new HospitalizedCat(new Date().getTime(), cat2);
		
		kieSession.insert(container);
		kieSession.insert(hcat1);
		kieSession.insert(hcat2);
		for(int i = 0; i < 40; i++) {
			kieSession.insert(new FluidFlowEvent(hcat1.getCat().getJmbm(), 5.0));
			kieSession.insert(new FluidFlowEvent(hcat1.getCat().getJmbm(), 0.5));
			kieSession.insert(new FluidFlowEvent(hcat2.getCat().getJmbm(), 0.5));
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		assertEquals(1, container.getNotifications().size());
	}
	
	@Test
	public void testFluidLevelEvent2() {
		// no sout
		KieBase kieBase = kieContainer.getKieBase("cep");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("fluid_level").setFocus();

		AlarmNotificationContainer container = new AlarmNotificationContainer();
		Cat cat1 = new Cat("001", "Kica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat1 = new HospitalizedCat(new Date().getTime(), cat1);
		Cat cat2 = new Cat("002", "Mica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat2 = new HospitalizedCat(new Date().getTime(), cat2);
		
		kieSession.insert(container);
		kieSession.insert(hcat1);
		kieSession.insert(hcat2);
		for(int i = 0; i < 40; i++) {
			kieSession.insert(new FluidFlowEvent(hcat1.getCat().getJmbm(), 5.0));
			kieSession.insert(new FluidFlowEvent(hcat1.getCat().getJmbm(), 0.5));
			kieSession.insert(new FluidFlowEvent(hcat2.getCat().getJmbm(), 5.0));
			kieSession.insert(new FluidFlowEvent(hcat2.getCat().getJmbm(), 0.5));
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		assertEquals(0, container.getNotifications().size());
	}
	
	@Test
	public void testFluidLevelEvent3() {
		// sout 001, 002
		KieBase kieBase = kieContainer.getKieBase("cep");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.getAgenda().getAgendaGroup("fluid_level").setFocus();

		AlarmNotificationContainer container = new AlarmNotificationContainer();
		Cat cat1 = new Cat("001", "Kica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat1 = new HospitalizedCat(new Date().getTime(), cat1);
		Cat cat2 = new Cat("002", "Mica", 5, 7, Breed.RUSKA, CatAge.ODRASLA_MACKA, Gender.MALE);
		HospitalizedCat hcat2 = new HospitalizedCat(new Date().getTime(), cat2);
		
		kieSession.insert(container);
		kieSession.insert(hcat1);
		kieSession.insert(hcat2);
		for(int i = 0; i < 40; i++) {
			kieSession.insert(new FluidFlowEvent(hcat1.getCat().getJmbm(), 0.8));
			kieSession.insert(new FluidFlowEvent(hcat1.getCat().getJmbm(), 0.5));
			kieSession.insert(new FluidFlowEvent(hcat2.getCat().getJmbm(), 0.8));
			kieSession.insert(new FluidFlowEvent(hcat2.getCat().getJmbm(), 0.5));
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		assertEquals(1, container.getNotifications().size());
	}

}
