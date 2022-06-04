package events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("5m")
public class OxygenOutEvent {
	private Double oxygenLevel;
	private String jmbm;

	public OxygenOutEvent() {}

	public OxygenOutEvent(Double oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}	
	
	public Double getOxygenLevel() {
		return oxygenLevel;
	}

	public void setOxygenLevel(Double oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}	
	
	public String getJmbm() {
		return jmbm;
	}

	public void setJmbm(String jmbm) {
		this.jmbm = jmbm;
	}
	
}
