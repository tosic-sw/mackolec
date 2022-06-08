package com.vet.mackolec.models.helper;

import com.vet.mackolec.events.AlarmNotificationEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlarmNotificationContainer {

	private AlarmNotificationEvent notification;

	public AlarmNotificationContainer() {}
	
}
