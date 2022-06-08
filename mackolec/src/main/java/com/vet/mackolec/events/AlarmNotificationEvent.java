package com.vet.mackolec.events;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vet.mackolec.models.enums.NotificationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Role(Role.Type.EVENT)
@Expires("30s")
@Entity
@Table(name = "alarm_notification")
public class AlarmNotificationEvent {
	
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;
	
    @Column(name = "jmbm", nullable=false)
	private String jmbm;
    
    @Column(name = "message", nullable=false)
	private String message;
    
    @Column(name = "dateTime", nullable=false)
    private Long dateTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "notificationType", nullable=false)
	private NotificationType notificationType;

	public AlarmNotificationEvent() {}

	public AlarmNotificationEvent(String jmbm, String message, NotificationType notificationType) {
		this.jmbm = jmbm;
		this.message = message;
		this.notificationType = notificationType; 
		this.dateTime = new Date().getTime();
	}
	
}
