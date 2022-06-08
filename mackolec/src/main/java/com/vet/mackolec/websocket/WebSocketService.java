package com.vet.mackolec.websocket;

import java.util.List;

import com.vet.mackolec.events.AlarmNotificationEvent;

public interface WebSocketService {

	void sendNotifications(List<AlarmNotificationEvent> alarmNotifications);
	
}
