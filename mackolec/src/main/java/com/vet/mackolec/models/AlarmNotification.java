package com.vet.mackolec.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vet.mackolec.models.enums.NotificationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alarm_notification")
public class AlarmNotification {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;
	
    @Column(name = "jmbm", nullable=false)
	private String jmbm;
    
    @Column(name = "message", nullable=false)
	private String message;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "notificationType", nullable=false)
	private NotificationType notificationType;

	public AlarmNotification() {}

	public AlarmNotification(String jmbm, String message, NotificationType notificationType) {
		this.jmbm = jmbm;
		this.message = message;
		this.notificationType = notificationType; 
	}
	
}
