package com.vet.mackolec.services;

import com.vet.mackolec.events.OxygenLevelEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;

public interface OxygenLevelService {
	
	void resonate(OxygenLevelEvent oxygenLevelEvent) throws HospitalizedCatException;

}
