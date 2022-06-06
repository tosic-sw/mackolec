package com.vet.mackolec.services;

import javax.annotation.PostConstruct;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.mackolec.events.OxygenLevelEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;
import com.vet.mackolec.models.HospitalizedCat;
import com.vet.mackolec.models.helper.AlarmNotificationContainer;
import com.vet.mackolec.websocket.WebSocketService;

@Service
public class OxygenLevelServiceImpl implements OxygenLevelService {

	private static final String AGENDA = "oxygen_level";
	
	private static KieSession kieSession;
	
	@Autowired
	private HospitalizedCatService hospitalizedCatService;
	
	@Autowired 
	private AlarmNotificationService alarmNotificationService;
	
	@Autowired
	private WebSocketService webSocketService;
	
	@PostConstruct
    private void postConstruct(){
		KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-kjar", "0.0.1-SNAPSHOT"));
        KieBase kieBase = kieContainer.getKieBase("cep");
        kieSession = kieBase.newKieSession();
        kieSession.getAgenda().getAgendaGroup(AGENDA).setFocus();
	}
	
	@Override
	public void resonate(OxygenLevelEvent oxygenLevelEvent) throws HospitalizedCatException {
		HospitalizedCat hospitalizedCat = hospitalizedCatService.findOneByJmbm(oxygenLevelEvent.getJmbm());
		if(hospitalizedCat == null) {
			throw new HospitalizedCatException(String.format("There is no hospitalized cat with sent jmbm: '%s'", oxygenLevelEvent.getJmbm()));
		}
		AlarmNotificationContainer container = new AlarmNotificationContainer();
		
		kieSession.insert(oxygenLevelEvent);
		kieSession.insert(container);
		kieSession.insert(hospitalizedCat);
		kieSession.fireAllRules();
		
		if(container.getNotifications().size() == 0) // Rule didn't create alarm notification
			return;
		
		alarmNotificationService.saveAll(container.getNotifications());
		webSocketService.sendNotifications(container.getNotifications());
	}

}
