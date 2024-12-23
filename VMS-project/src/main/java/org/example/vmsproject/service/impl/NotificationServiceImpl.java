package org.example.vmsproject.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.vmsproject.dto.NotificationDTO;
import org.example.vmsproject.entity.ENotification;
import org.example.vmsproject.entity.Notification;
import org.example.vmsproject.entity.User;
import org.example.vmsproject.entity.UserNotification;
import org.example.vmsproject.repository.NotificationRepository;
import org.example.vmsproject.repository.UserNotificationRepository;
import org.example.vmsproject.repository.UserRepository;
import org.example.vmsproject.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public String createAndSendNotification(String username, NotificationDTO notificationDTO) {
        // Find the user by username
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Create a new Notification
            Notification notification = new Notification();
            notification.setTitle(notificationDTO.getTitle());
            notification.setContent(notificationDTO.getContent());
            notification.setCreatedAt(LocalDateTime.now());
            notification.setType(notificationDTO.getType());
            notification.setRead(false);
            notificationRepository.save(notification);

            // Link the Notification to the User
            UserNotification userNotification = new UserNotification();
            userNotification.setUser(user);
            userNotification.setNotification(notification);
            userNotificationRepository.save(userNotification);

//            messagingTemplate.convertAndSendToUser(username, "/notifications", notificationDTO);
            return "Create Notification Successfully";
        } else {
            // Return an error message if the user is not found
            throw new EntityNotFoundException("User not found with username: " + username);
        }
    }


    @Override
    public List<UserNotification> getNotificationsByUsername(String username) {
        return userNotificationRepository.findByUserUsername(username);
    }

    @Override
    public List<UserNotification>getAllNotifications() {
        return userNotificationRepository.findAll();
    }
}
