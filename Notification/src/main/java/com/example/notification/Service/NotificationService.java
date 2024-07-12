package com.example.notification.Service;

import com.example.notification.Model.Notification;
import com.example.notification.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    public Notification getNotificationById(Long id){
        return notificationRepository.findById(id).orElse(null);
    }


    public Notification createNotification(Notification notification){
        return notificationRepository.save(notification);
    }
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
