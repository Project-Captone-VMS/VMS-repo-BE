package org.example.vmsproject.controller;

import org.example.vmsproject.dto.NotificationDTO;
import org.example.vmsproject.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{username}")
    public void sendNotificationToUser(@DestinationVariable String username, @Payload NotificationDTO notificationDTO) {
        // Persist or process the notification
        notificationService.createAndSendNotification(username, notificationDTO);

        // Send the notification to the specific user
        messagingTemplate.convertAndSendToUser(username, "/notifications", notificationDTO);
    }
}
