package org.example.vmsproject.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.vmsproject.entity.Role;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String password;
    LocalDate dob;
    Set<Role> roles;
    String firstName;
    String lastName;
}