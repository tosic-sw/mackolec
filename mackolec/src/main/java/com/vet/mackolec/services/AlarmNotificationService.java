package com.vet.mackolec.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vet.mackolec.models.AlarmNotification;

public interface AlarmNotificationService {
	
	void save(AlarmNotification alarmNotification);
	
	void saveAll(List<AlarmNotification> alarmNotifications);

	Page<AlarmNotification> search(Pageable pageable);
}
