package com.vet.mackolec.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vet.mackolec.events.AlarmNotificationEvent;
import com.vet.mackolec.models.enums.NotificationType;
import com.vet.mackolec.repositories.AlarmNotificationRepository;
import com.vet.mackolec.services.AlarmNotificationService;

@Service
public class AlarmNotificationServiceImpl implements AlarmNotificationService {

	@Autowired
	private AlarmNotificationRepository alarmNotificationRepository;
	
	@Override
	public void save(AlarmNotificationEvent alarmNotification) {
		alarmNotificationRepository.save(alarmNotification);
	}

	@Override
	public Page<AlarmNotificationEvent> search(String notificationType, Pageable pageable) {
		if(notificationType.equals(""))
			return alarmNotificationRepository.findAll(pageable);
		
		return alarmNotificationRepository.findAllByNotificationType(NotificationType.valueOf(notificationType), pageable);
	}

	@Override
	public void saveAll(List<AlarmNotificationEvent> alarmNotifications) {
		alarmNotificationRepository.saveAll(alarmNotifications);
	}

}
