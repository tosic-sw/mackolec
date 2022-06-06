package com.vet.mackolec.websocket;

import java.util.List;

import com.vet.mackolec.models.AlarmNotification;

public interface WebSocketService {

	void sendNotifications(List<AlarmNotification> alarmNotifications);
	
}
