package com.vet.mackolec.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Role(Role.Type.EVENT)
@Expires("5m")
public class OxygenLevelEvent {
	private Double oxygenLevel;
	private String jmbm;

	public OxygenLevelEvent() {}

	public OxygenLevelEvent(Double oxygenLevel) {
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
