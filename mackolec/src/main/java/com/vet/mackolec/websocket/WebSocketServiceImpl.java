package com.vet.mackolec.websocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.vet.mackolec.models.AlarmNotification;

@Service
public class WebSocketServiceImpl implements WebSocketService {

	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	
	@Override
	public void sendNotifications(List<AlarmNotification> alarmNotifications) {
		for(AlarmNotification an : alarmNotifications) {
            Map<String, String> message = new HashMap<>();
            message.put("message", an.getMessage());
            message.put("jmbm", an.getJmbm());

            this.simpMessagingTemplate.convertAndSend(String.format("/socket-publisher"), message);
        }
	}

}
