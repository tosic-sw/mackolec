package com.vet.mackolec.events;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Role(Role.Type.EVENT)
@Expires("5m")
public class OxygenLevelEvent implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Double oxygenLevel;
	private String jmbm;

	public OxygenLevelEvent() {}

	public OxygenLevelEvent(String jmbm, Double oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
		this.jmbm = jmbm;
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
