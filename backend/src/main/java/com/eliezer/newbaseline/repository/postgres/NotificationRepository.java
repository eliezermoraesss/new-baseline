package com.eliezer.newbaseline.repository.postgres;

import com.eliezer.newbaseline.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
