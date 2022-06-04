package events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("1m")
public class HartBeatEvent {
	private String jmbm;
	
	public HartBeatEvent() {}
	
	public String getJmbm() {
		return jmbm;
	}

	public void setJmbm(String jmbm) {
		this.jmbm = jmbm;
	}
}
