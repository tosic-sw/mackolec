package events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

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

	public double getFluidFlow() {
		return fluidFlow;
	}

	public void setFluidFlow(double fluidFlow) {
		this.fluidFlow = fluidFlow;
	}

	public String getJmbm() {
		return jmbm;
	}

	public void setJmbm(String jmbm) {
		this.jmbm = jmbm;
	}
	
}
