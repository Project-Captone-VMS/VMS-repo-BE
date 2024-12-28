package org.example.vmsproject.repository;

import org.example.vmsproject.entity.UserNotification;
import org.example.vmsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    List<UserNotification> findByUserUsername(String username);
}
