package com.vet.mackolec.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.vet.mackolec.events.AlarmNotificationEvent;
import com.vet.mackolec.events.FluidFlowEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;
import com.vet.mackolec.models.HospitalizedCat;
import com.vet.mackolec.services.AlarmNotificationService;
import com.vet.mackolec.services.FluidFlowService;
import com.vet.mackolec.services.HospitalizedCatService;
import com.vet.mackolec.websocket.WebSocketService;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FluidFlowServiceImpl implements FluidFlowService {

	private static final String AGENDA = "fluid_level";
	
	@Autowired
	private HospitalizedCatService hospitalizedCatService;
	
	@Autowired 
	private AlarmNotificationService alarmNotificationService;
	
	@Autowired
	private WebSocketService webSocketService;
	
	@Autowired
	@Qualifier(value = "cep-session-fluid")
	private KieSession kieSession;
	
	
	@Override
	public void resonate(FluidFlowEvent fluidLevelEvent) throws HospitalizedCatException {
		HospitalizedCat hospitalizedCat = hospitalizedCatService.findOneByJmbm(fluidLevelEvent.getJmbm());
		if(hospitalizedCat == null) {
			throw new HospitalizedCatException(String.format("There is no hospitalized cat with sent jmbm: '%s'", fluidLevelEvent.getJmbm()));
		}
		
		kieSession.insert(fluidLevelEvent);
		kieSession.fireAllRules();
		resetAgenda(kieSession, AGENDA);

		Object[] ans = kieSession.getObjects(new ClassObjectFilter(AlarmNotificationEvent.class)).toArray();
		if(ans.length == 0) { // Rule didn't create alarm notification
			return;
		}
		
		AlarmNotificationEvent alarmNotification = (AlarmNotificationEvent) ans[0];
		if(alarmNotification.getId() == null) {
			alarmNotificationService.save(alarmNotification);
			List<AlarmNotificationEvent> notifications = new ArrayList<AlarmNotificationEvent>();
			notifications.add(alarmNotification);
			webSocketService.sendNotifications(notifications);
		}
	}
	
	@Override
	public void resetAgenda(KieSession kieSession, String agenda) {
		kieSession.getAgenda().getAgendaGroup(agenda).setFocus();
	}

}
