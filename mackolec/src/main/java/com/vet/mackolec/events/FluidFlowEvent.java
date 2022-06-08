package com.vet.mackolec.events;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Role(Role.Type.EVENT)
@Expires("1m")
public class FluidFlowEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double fluidFlow;
	private String jmbm;
	
	public FluidFlowEvent() {}
	
	public FluidFlowEvent(String jmbm, Double fluidFlow) {
		this.jmbm = jmbm;
		this.fluidFlow = fluidFlow;
	}
}
