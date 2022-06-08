package com.vet.mackolec.services;

import org.kie.api.runtime.KieSession;

import com.vet.mackolec.events.FluidFlowEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;

public interface FluidFlowService {

	void resonate(FluidFlowEvent fluidLevelEvent) throws HospitalizedCatException;
	
	void resetAgenda(KieSession kieSession, String agenda);
}
