package com.vet.mackolec.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alarm_notification")
public class AlarmNotification {
	private String jmbm;
	private String message;

	public AlarmNotification() {}

	public AlarmNotification(String jmbm, String message) {
		this.jmbm = jmbm;
		this.message = message;
	}
	
	public String getJmbm() {
		return jmbm;
	}

	public void setJmbm(String jmbm) {
		this.jmbm = jmbm;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
