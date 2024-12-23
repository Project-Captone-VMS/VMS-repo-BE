package org.example.vmsproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.vmsproject.dto.NotificationDTO;
import org.example.vmsproject.entity.ENotification;
import org.example.vmsproject.entity.Notification;
import org.example.vmsproject.entity.UserNotification;
import org.example.vmsproject.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send/{username}")
    public ResponseEntity<String> createAndSendNotification(
            @PathVariable String username,
            @RequestBody NotificationDTO notificationDTO) {
        try {
            String message = notificationService.createAndSendNotification(username, notificationDTO);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<UserNotification>> getNotificationsByUsername(@PathVariable String username) {
        List<UserNotification> notifications = notificationService.getNotificationsByUsername(username);
        if (notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserNotification>> getAllNotifications() {
        List<UserNotification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }
}
