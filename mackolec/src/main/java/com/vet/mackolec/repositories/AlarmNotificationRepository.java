package com.vet.mackolec.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vet.mackolec.models.AlarmNotification;
import com.vet.mackolec.models.enums.NotificationType;

@Repository
public interface AlarmNotificationRepository extends JpaRepository<AlarmNotification, Long>{

	Page<AlarmNotification> findAll(Pageable pageable);
	
//	@Query("select notification from AlarmNotification notification where notification.notificationType = :type or :type = ''")
//	Page<AlarmNotification> findAllByNotificationType(@Param("type") String notificationType, Pageable pageable);
	
	Page<AlarmNotification> findAllByNotificationType(NotificationType notificationType, Pageable pageable);
}
