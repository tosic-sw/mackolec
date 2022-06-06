package com.vet.mackolec.services;

import com.vet.mackolec.events.FluidFlowEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;

public interface FluidFlowService {

	void resonate(FluidFlowEvent fluidLevelEvent) throws HospitalizedCatException;
	
}
