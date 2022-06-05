package com.vet.mackolec.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Role(Role.Type.EVENT)
@Expires("1m")
public class HeartBeatEvent {
	private String jmbm;
	
	public HeartBeatEvent() {}
	
	public HeartBeatEvent(String jmbm) {
		this.jmbm = jmbm;
	}
}
