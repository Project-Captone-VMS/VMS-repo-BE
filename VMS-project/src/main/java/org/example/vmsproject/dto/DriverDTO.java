package org.example.vmsproject.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    private Long driverId;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String workSchedule;
    private String status;
}
