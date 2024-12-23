package org.example.vmsproject.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UpdateUserRequest {
    String username;
    String firstName;
    String lastName;
    String password;
    String email;
    String phoneNumber;
    LocalDate dob;
    List<String> roles;

//    @Size(min = 6, message = "USERNAME_INVALID")
//    String username;
//    @Size(min = 8, message = "INVALID_PASSWORD")
//    String password;

}
