package com.example.notification.Controller;

import com.example.notification.Model.Notification;
import com.example.notification.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications(){
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id){
        Notification notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notification);
    }
    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification){
        Notification createdNotification = notificationService.createNotification(notification);
        return ResponseEntity.status(201).body(createdNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
