package com.vet.mackolec.services;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.drools.core.ClassObjectFilter;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.mackolec.events.HeartBeatEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;
import com.vet.mackolec.models.HospitalizedCat;
import com.vet.mackolec.models.helper.AlarmNotificationContainer;
import com.vet.mackolec.websocket.WebSocketService;

@Service
public class HeartBeatServiceImpl implements HeartBeatService {

	private static final String AGENDA = "heart_beat";
	
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void resonate(HeartBeatEvent heartBeatEvent) throws HospitalizedCatException {
		HospitalizedCat hospitalizedCat = hospitalizedCatService.findOneByJmbm(heartBeatEvent.getJmbm());
		if(hospitalizedCat == null) {
			throw new HospitalizedCatException(String.format("There is no hospitalized cat with sent jmbm: '%s'", heartBeatEvent.getJmbm()));
		}
		AlarmNotificationContainer container = new AlarmNotificationContainer();
		
		kieSession.insert(heartBeatEvent);
		FactHandle containerHandle = kieSession.insert(container);
		FactHandle hospitalizedCatHandle = kieSession.insert(hospitalizedCat);
		
		Collection<?> hcs = kieSession.getObjects(new ClassObjectFilter(HospitalizedCat.class));
		HospitalizedCat hc = (HospitalizedCat) hcs.toArray()[0];
		System.err.println(heartBeatEvent.getJmbm().equals(hc.getCat().getJmbm()));
		
		kieSession.fireAllRules();
		
		kieSession.retract(hospitalizedCatHandle);
		kieSession.retract(containerHandle);
		
		if(container.getNotifications().size() == 0) { // Rule didn't create alarm notification
			return;
		}
		
		System.err.println("################# Should send notification #################");
		alarmNotificationService.saveAll(container.getNotifications());
		webSocketService.sendNotifications(container.getNotifications());
	}

}
