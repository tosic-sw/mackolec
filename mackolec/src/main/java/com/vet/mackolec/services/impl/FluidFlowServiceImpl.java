package com.vet.mackolec.services.impl;

import javax.annotation.PostConstruct;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.vet.mackolec.events.FluidFlowEvent;
import com.vet.mackolec.services.FluidFlowService;

public class FluidFlowServiceImpl implements FluidFlowService {

	private static final String AGENDA = "fluid_level";
	
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
	public void resonate(FluidFlowEvent fluidLevelEvent) {
		// Add notification to data base
		// Sent notification through socket
	}

}
