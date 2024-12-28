package org.example.vmsproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.vmsproject.entity.User;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UpdateDriverRequest {
//    private Long driverId;
//    private String firstName;
//    private String lastName;
    private String phoneNumber;
    private String licenseNumber;
    private String workSchedule;
//    private Boolean status;
    private String email;
    private User user;
//    private Boolean isDeleted = false;
//    private LocalDateTime deleteAt;
}