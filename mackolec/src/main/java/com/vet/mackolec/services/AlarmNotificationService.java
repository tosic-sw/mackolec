package com.vet.mackolec.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vet.mackolec.models.AlarmNotification;

public interface AlarmNotificationService {
	
	void save(AlarmNotification alarmNotification);

	Page<AlarmNotification> search(Pageable pageable);
}
