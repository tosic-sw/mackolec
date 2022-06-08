package com.vet.mackolec.models.helper;

import com.vet.mackolec.models.AlarmNotification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlarmNotificationContainer {

	private AlarmNotification notification;

	public AlarmNotificationContainer() {}
	
}
