package org.example.vmsproject.service;

import org.example.vmsproject.dto.NotificationDTO;

public interface NotificationService {
    void sendNotificationToUser(String username, NotificationDTO notification);
    String createNotification(NotificationDTO notification);
}
