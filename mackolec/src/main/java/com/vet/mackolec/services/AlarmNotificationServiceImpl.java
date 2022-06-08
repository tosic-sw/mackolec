package com.vet.mackolec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vet.mackolec.models.AlarmNotification;
import com.vet.mackolec.models.enums.NotificationType;
import com.vet.mackolec.repositories.AlarmNotificationRepository;

@Service
public class AlarmNotificationServiceImpl implements AlarmNotificationService {

	@Autowired
	private AlarmNotificationRepository alarmNotificationRepository;
	
	@Override
	public void save(AlarmNotification alarmNotification) {
		alarmNotificationRepository.save(alarmNotification);
	}

	@Override
	public Page<AlarmNotification> search(String notificationType, Pageable pageable) {
		if(notificationType.equals(""))
			return alarmNotificationRepository.findAll(pageable);
		
		return alarmNotificationRepository.findAllByNotificationType(NotificationType.valueOf(notificationType), pageable);
	}

	@Override
	public void saveAll(List<AlarmNotification> alarmNotifications) {
		alarmNotificationRepository.saveAll(alarmNotifications);
	}

}
