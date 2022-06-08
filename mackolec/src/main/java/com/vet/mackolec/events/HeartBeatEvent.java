package com.vet.mackolec.events;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Role(Role.Type.EVENT)
@Expires("2m")
public class HeartBeatEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String jmbm;
	
	public HeartBeatEvent() {}
	
	public HeartBeatEvent(String jmbm) {
		this.jmbm = jmbm;
	}
	
}
