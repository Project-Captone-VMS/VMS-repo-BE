package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.NotificationDTO;
import org.example.vmsproject.entity.ENotification;
import org.example.vmsproject.entity.Notification;
import org.example.vmsproject.entity.User;
import org.example.vmsproject.entity.UserNotification;
import org.example.vmsproject.repository.NotificationRepository;
import org.example.vmsproject.repository.UserNotificationRepository;
import org.example.vmsproject.repository.UserRepository;
import org.example.vmsproject.service.NotificationService;
import org.example.vmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserNotificationRepository userNotificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendNotificationToUser(String username, NotificationDTO notificationDTO) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Notification notification = new Notification();
            notification.setTitle(notificationDTO.getTitle());
            notification.setContent(notificationDTO.getContent());
            notification.setType(notificationDTO.getType());
            notification.setRead(false);
            notification.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(notification);

            UserNotification userNotification = new UserNotification();
            userNotification.setUser(user.get());
            userNotification.setNotification(notification);
            userNotificationRepository.save(userNotification);
//            messagingTemplate.convertAndSendToUser(user.get().getUsername(), "/queue/notifications", notification);
        }else{
            System.out.println("User not found" + username);
        }
    }

    @Override
    public String createNotification(NotificationDTO notification) {
        Notification noti = new Notification();
        noti.setTitle(notification.getTitle());
        noti.setContent(notification.getContent());
        noti.setType(notification.getType());
        noti.setCreatedAt(LocalDateTime.now());
        noti.setRead(false);
        notificationRepository.save(noti);
        return "Create Notification Successfully";
    }
}
