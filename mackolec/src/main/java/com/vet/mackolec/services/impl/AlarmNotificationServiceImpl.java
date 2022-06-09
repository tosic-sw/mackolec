package com.vet.mackolec.services.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vet.mackolec.models.AlarmNotification;
import com.vet.mackolec.repositories.AlarmNotificationRepository;
import com.vet.mackolec.services.AlarmNotificationService;

@Service
public class AlarmNotificationServiceImpl implements AlarmNotificationService {

	@Autowired
	private AlarmNotificationRepository alarmNotificationRepository;
	
	@Override
	public void save(AlarmNotification alarmNotification) {
		alarmNotificationRepository.save(alarmNotification);
	}

	@Override
	public Page<AlarmNotification> search(Pageable pageable) {
		return alarmNotificationRepository.findAll(pageable);
	}

}
