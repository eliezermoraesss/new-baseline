package com.eliezer.newbaseline.repository;

import com.eliezer.newbaseline.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
