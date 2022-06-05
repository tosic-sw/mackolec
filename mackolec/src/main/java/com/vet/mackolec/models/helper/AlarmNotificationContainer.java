package com.vet.mackolec.models.helper;

import java.util.ArrayList;
import java.util.List;

import com.vet.mackolec.models.AlarmNotification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlarmNotificationContainer {

	private List<AlarmNotification> notifications;

	public AlarmNotificationContainer() {
		this.notifications = new ArrayList<AlarmNotification>();
	}
	
	public void addNotification(AlarmNotification notification) {
		notifications.add(notification);
	}
	
}
