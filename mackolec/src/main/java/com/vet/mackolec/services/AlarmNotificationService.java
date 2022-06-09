package com.vet.mackolec.services;

import java.util.List;

import com.vet.mackolec.events.AlarmNotificationEvent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlarmNotificationService {
	
	void save(AlarmNotificationEvent alarmNotification);
	
	void saveAll(List<AlarmNotificationEvent> alarmNotifications);

	Page<AlarmNotificationEvent> search(String notificationType, Pageable pageable);
}
