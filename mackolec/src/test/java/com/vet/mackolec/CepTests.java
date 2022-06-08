package com.vet.mackolec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.vet.mackolec.events.FluidFlowEvent;
import com.vet.mackolec.events.HeartBeatEvent;
import com.vet.mackolec.events.OxygenLevelEvent;
import com.vet.mackolec.models.AlarmNotification;

public class CepTests {

	private static KieContainer kieContainer;
	  
	@Before
    public void beforeClass() {
		KieServices kieServices = KieServices.Factory.get();
		kieContainer = kieServices.newKieClasspathContainer();
    }
	
	@Test
	public void testHeartBeatEvent_1() throws InterruptedException {
		KieSession kieSession = kieContainer.newKieSession("cep-session-pseudo");
		kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();
		SessionPseudoClock clock = kieSession.getSessionClock();
				
		for(int i = 0; i < 36; i++) {
			kieSession.insert(new HeartBeatEvent("001"));
			clock.advanceTime(1, TimeUnit.SECONDS);
			kieSession.fireAllRules();
			kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();
		}
		
		kieSession.dispose();
		
		Object[] ans = kieSession.getObjects(new ClassObjectFilter(AlarmNotification.class)).toArray();
		AlarmNotification alarmNotification = (AlarmNotification) ans[0];
		assertEquals("001", alarmNotification.getJmbm());
	}
	
	
	@Test
	public void testHeartBeatEvent_2() throws InterruptedException {
		KieSession kieSession = kieContainer.newKieSession("cep-session-pseudo");
		kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();
		SessionPseudoClock clock = kieSession.getSessionClock();
				
		for(int i = 0; i < 30; i++) {
			kieSession.insert(new HeartBeatEvent("001"));
			clock.advanceTime(1, TimeUnit.SECONDS);
			kieSession.fireAllRules();
			kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();
		}
		
		kieSession.dispose();
		
		Object[] ans = kieSession.getObjects(new ClassObjectFilter(AlarmNotification.class)).toArray();
		assertEquals(0, ans.length);
	}
	
	@Test
	public void testHeartBeatEvent_3() throws InterruptedException {
		KieSession kieSession = kieContainer.newKieSession("cep-session-pseudo");
		kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();
		SessionPseudoClock clock = kieSession.getSessionClock();
				
		for(int i = 0; i < 60; i++) {
			kieSession.insert(new HeartBeatEvent("001"));
			clock.advanceTime(2, TimeUnit.SECONDS);
			kieSession.fireAllRules();
			kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();
		}
		
		kieSession.dispose();
		
		Object[] ans = kieSession.getObjects(new ClassObjectFilter(AlarmNotification.class)).toArray();
		assertEquals(0, ans.length);
	}
	
	
	@Test
	public void testOxygenLevelEvent_1() {
		KieSession kieSession = kieContainer.newKieSession("cep-session-pseudo");
		kieSession.getAgenda().getAgendaGroup("oxygen_level").setFocus();
		SessionPseudoClock clock = kieSession.getSessionClock();

		for(int i = 0; i < 15; i++) {
			kieSession.insert(new OxygenLevelEvent("001", 95.0));
			clock.advanceTime(5, TimeUnit.SECONDS);
			kieSession.fireAllRules();
			kieSession.getAgenda().getAgendaGroup("oxygen_level").setFocus();
		}
		
		kieSession.dispose();

		Object[] ans = kieSession.getObjects(new ClassObjectFilter(AlarmNotification.class)).toArray();
		assertEquals(0, ans.length);
	}
	
	@Test
	public void testOxygenLevelEvent_2() {
		KieSession kieSession = kieContainer.newKieSession("cep-session-pseudo");
		kieSession.getAgenda().getAgendaGroup("oxygen_level").setFocus();
		SessionPseudoClock clock = kieSession.getSessionClock();

		for(int i = 0; i < 90; i++) {
			kieSession.insert(new OxygenLevelEvent("001", 93.0));
			clock.advanceTime(2, TimeUnit.SECONDS);
			kieSession.fireAllRules();
			kieSession.getAgenda().getAgendaGroup("oxygen_level").setFocus();
		}
		
		kieSession.dispose();

		Object[] ans = kieSession.getObjects(new ClassObjectFilter(AlarmNotification.class)).toArray();
		AlarmNotification alarmNotification = (AlarmNotification) ans[0];
		assertEquals("001", alarmNotification.getJmbm());
	}
	
	@Test
	public void testFluidLevelEvent_1() {
		KieSession kieSession = kieContainer.newKieSession("cep-session-pseudo");
		kieSession.getAgenda().getAgendaGroup("fluid_level").setFocus();
		SessionPseudoClock clock = kieSession.getSessionClock();

		for(int i = 0; i < 20; i++) {
			kieSession.insert(new FluidFlowEvent("001", 5.0));
			kieSession.insert(new FluidFlowEvent("001", 0.5));
			clock.advanceTime(500, TimeUnit.MILLISECONDS);
			kieSession.fireAllRules();
			kieSession.getAgenda().getAgendaGroup("fluid_level").setFocus();
		}
		
		kieSession.dispose();
		
		Object[] ans = kieSession.getObjects(new ClassObjectFilter(AlarmNotification.class)).toArray();
		assertEquals(0, ans.length);
	}
	
	@Test
	public void testFluidLevelEvent_2() {
		KieSession kieSession = kieContainer.newKieSession("cep-session-pseudo");
		kieSession.getAgenda().getAgendaGroup("fluid_level").setFocus();
		SessionPseudoClock clock = kieSession.getSessionClock();

		for(int i = 0; i < 35; i++) {
			kieSession.insert(new FluidFlowEvent("001", 0.5));
			clock.advanceTime(1, TimeUnit.SECONDS);
			kieSession.fireAllRules();
			kieSession.getAgenda().getAgendaGroup("fluid_level").setFocus();
		}
		
		kieSession.dispose();
		
		Object[] ans = kieSession.getObjects(new ClassObjectFilter(AlarmNotification.class)).toArray();
		AlarmNotification alarmNotification = (AlarmNotification) ans[0];
		assertEquals("001", alarmNotification.getJmbm());
	}
	



}
