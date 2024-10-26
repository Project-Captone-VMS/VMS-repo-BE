package org.example.vmslogin.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.vmslogin.entity.Role;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;
    boolean authenticated;
    private Set<String> roles;

}
