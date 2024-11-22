package org.example.vmsproject.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vmsproject.entity.ENotification;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private String username;
    private String content;
    private String title;
    private boolean isRead = false;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private ENotification type;
}
