package org.example.vmsproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DriverRequest {
    private Long driverId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String licenseNumber;
    private String workSchedule;
    private Boolean status;
    private String email;
//    private Boolean isDeleted = false;
//    private LocalDateTime deleteAt;
}
