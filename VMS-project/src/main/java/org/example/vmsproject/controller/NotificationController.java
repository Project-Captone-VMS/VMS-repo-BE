package org.example.vmsproject.controller;

import org.example.vmsproject.dto.NotificationDTO;
import org.example.vmsproject.entity.ENotification;
import org.example.vmsproject.entity.UserNotification;
import org.example.vmsproject.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;


    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDTO notificationDTO) {
        try {
            if (notificationDTO == null || notificationDTO.getUsername() == null ||
                    notificationDTO.getTitle() == null || notificationDTO.getContent() == null ||
                    notificationDTO.getType() == null) {
                return ResponseEntity.badRequest().body("Invalid request data");
            }

            notificationService.sendNotificationToUser(
                    notificationDTO.getUsername(),
                    notificationDTO
            );
            return ResponseEntity.ok("Notification sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while sending notification");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNotification(@RequestBody NotificationDTO request) {
        String results = notificationService.createNotification(request);
        return ResponseEntity.ok(results);
    }
}
