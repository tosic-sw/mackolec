package com.vet.mackolec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.mackolec.models.AlarmNotification;
import com.vet.mackolec.services.AlarmNotificationService;

@RestController
@RequestMapping("api/alarmNotifications")
public class AlarmNotificationController {
	
    @Autowired
    private AlarmNotificationService alarmNotificationService;
	
	@GetMapping
    public ResponseEntity<List<AlarmNotification>> search(Pageable pageable) {
        try {
        	Page<AlarmNotification> alarmNotifications = alarmNotificationService.search(pageable);
            return new ResponseEntity<>(alarmNotifications.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<AlarmNotification>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
