package com.vet.mackolec.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Role(Role.Type.EVENT)
@Expires("1m")
public class FluidFlowEvent {
	private double fluidFlow;
	private String jmbm;
	
	public FluidFlowEvent() {}
	
	public FluidFlowEvent(Double fluidFlow, String jmbm) {
		this.jmbm = jmbm;
		this.fluidFlow = fluidFlow;
	}
}
