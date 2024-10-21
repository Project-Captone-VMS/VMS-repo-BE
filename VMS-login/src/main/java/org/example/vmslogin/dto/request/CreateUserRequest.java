package org.example.vmslogin.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.vmslogin.validator.DobConstraint;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @Size(min = 5, message = "USERNAME_INVALID")
    String username;
    @Size(min = 7, message = "INVALID_PASSWORD")
    String password;
    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
}
