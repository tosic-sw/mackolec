package com.vet.mackolec.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vet.mackolec.models.AlarmNotification;

@Repository
public interface AlarmNotificationRepository extends JpaRepository<AlarmNotification, Long>{

	Page<AlarmNotification> findAll(Pageable pageable);
	
}
