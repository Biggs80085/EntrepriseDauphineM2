package com.example.notification.Repository;

import com.example.notification.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
