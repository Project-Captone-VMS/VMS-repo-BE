package org.example.vmsproject.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    Long id;
//    @Size(min = 5, message = "USERNAME_INVALID")
    String username;
//    @Size(min = 7, message = "INVALID_PASSWORD")
    String password;
//    @DobConstraint(min = 18, message = "INVALID_DOB")
//    LocalDate dob;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
}
