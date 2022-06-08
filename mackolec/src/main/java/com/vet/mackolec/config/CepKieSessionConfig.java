package com.vet.mackolec.config;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CepKieSessionConfig {
	
	@Autowired
	private KieContainer kieContainer;
	
	@Bean(name = "cep-session-heart") 
	public KieSession cepHeartSession() {
		KieSession kieSession = kieContainer.newKieSession("cep-session");
		kieSession.getAgenda().getAgendaGroup("heart_beat").setFocus();
		return kieSession;
	}
	
	@Bean(name = "cep-session-oxygen") 
	public KieSession cepOxygenSession() {
		KieSession kieSession = kieContainer.newKieSession("cep-session");
		kieSession.getAgenda().getAgendaGroup("oxygen_level").setFocus();
		return kieSession;
	}
	
	@Bean(name = "cep-session-fluid") 
	public KieSession cepFluidSession() {
		KieSession kieSession = kieContainer.newKieSession("cep-session");
		kieSession.getAgenda().getAgendaGroup("fluid_level").setFocus();
		return kieSession;
	}

}
