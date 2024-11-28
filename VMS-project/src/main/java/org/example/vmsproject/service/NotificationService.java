package org.example.vmsproject.service;

import org.example.vmsproject.dto.NotificationDTO;
import org.example.vmsproject.entity.ENotification;
import org.example.vmsproject.entity.Notification;
import org.example.vmsproject.entity.UserNotification;

import java.util.List;

public interface NotificationService {
    String createAndSendNotification(String username, NotificationDTO notificationDTO);
    List<UserNotification> getNotificationsByUsername(String username);
    List<UserNotification> getAllNotifications();
}
