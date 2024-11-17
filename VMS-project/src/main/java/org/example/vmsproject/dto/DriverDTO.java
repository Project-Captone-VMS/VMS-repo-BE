package org.example.vmsproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;

    @NotBlank(message = "License number is required.")
    @Size(max = 20, message = "License number cannot exceed 20 characters.")
    private String licenseNumber;

    @NotBlank(message = "Work schedule is required.")
    private String workSchedule;

    @NotBlank(message = "Status is required.")
    private String status;

    private String email;
    private String phoneNumber;
    private Boolean isDeleted;
    private LocalDateTime deleteAt;
}
