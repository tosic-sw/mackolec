package com.vet.mackolec.services;

import org.kie.api.runtime.KieSession;

import com.vet.mackolec.events.OxygenLevelEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;

public interface OxygenLevelService {
	
	void resonate(OxygenLevelEvent oxygenLevelEvent) throws HospitalizedCatException;
	
	void resetAgenda(KieSession kieSession, String agenda);
}
