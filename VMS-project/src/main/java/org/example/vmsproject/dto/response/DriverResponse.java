package org.example.vmsproject.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverResponse {
    private Long driverId;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String workSchedule;
    private String status;
    private Boolean isDeleted = false;
    private LocalDateTime deleteAt;
}
