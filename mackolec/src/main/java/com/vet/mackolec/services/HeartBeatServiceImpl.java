package com.vet.mackolec.services;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vet.mackolec.events.HeartBeatEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;
import com.vet.mackolec.models.AlarmNotification;
import com.vet.mackolec.models.HospitalizedCat;
import com.vet.mackolec.websocket.WebSocketService;

@Service
public class HeartBeatServiceImpl implements HeartBeatService {
	
	private static final String AGENDA = "heart_beat";
	
	@Autowired
	private HospitalizedCatService hospitalizedCatService;
	
	@Autowired 
	private AlarmNotificationService alarmNotificationService;
	
	@Autowired
	private WebSocketService webSocketService;
	
	@Autowired
	@Qualifier(value = "cep-session-heart")
	private KieSession kieSession;
	
	
	@Override
	public void resonate(HeartBeatEvent heartBeatEvent) throws HospitalizedCatException {
		HospitalizedCat hospitalizedCat = hospitalizedCatService.findOneByJmbm(heartBeatEvent.getJmbm());
		if(hospitalizedCat == null) {
			throw new HospitalizedCatException(String.format("There is no hospitalized cat with sent jmbm: '%s'", heartBeatEvent.getJmbm()));
		}
		
		kieSession.insert(heartBeatEvent);
		kieSession.fireAllRules();
		resetAgenda(kieSession, AGENDA);

		Object[] ans = kieSession.getObjects(new ClassObjectFilter(AlarmNotification.class)).toArray();
		if(ans.length != 1) { // Rule didn't create alarm notification
			return;
		}
		
		AlarmNotification alarmNotification = (AlarmNotification) ans[0];
		alarmNotificationService.save(alarmNotification);
		List<AlarmNotification> notifications = new ArrayList<AlarmNotification>();
		notifications.add(alarmNotification);
		webSocketService.sendNotifications(notifications);
	}

	@Override
	public void resetAgenda(KieSession kieSession, String agenda) {
		kieSession.getAgenda().getAgendaGroup(agenda).setFocus();
	}
}
