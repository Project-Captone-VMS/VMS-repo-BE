package org.example.vmsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private ENotification type;

    private boolean isRead;   // Trạng thái đã đọc của thông báo

    private LocalDateTime createdAt;

//    @OneToMany(mappedBy = "notification")
//    private List<UserNotification> userNotifications;
}
