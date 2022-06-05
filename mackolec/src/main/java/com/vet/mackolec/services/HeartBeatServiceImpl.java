package com.vet.mackolec.services;

import javax.annotation.PostConstruct;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.vet.mackolec.events.HeartBeatEvent;

public class HeartBeatServiceImpl implements HeartBeatService {

	private static final String AGENDA = "heart_beat";
	
	private static KieSession kieSession;
	
	@PostConstruct
    private void postConstruct(){
		KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
        KieBase kieBase = kieContainer.getKieBase("cep");
        kieSession = kieBase.newKieSession();
        kieSession.getAgenda().getAgendaGroup(AGENDA).setFocus();
	}
	
	@Override
	public void resonate(HeartBeatEvent heartBeatEvent) {
		// Add notification to data base
		// Sent notification through socket
	}

}
