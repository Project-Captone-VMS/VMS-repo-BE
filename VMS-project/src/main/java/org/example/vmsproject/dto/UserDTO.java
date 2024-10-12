package org.example.vmsproject.dto;


import lombok.*;
import org.example.vmsproject.entity.Role;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;

    public UserDTO(Long userId, String username, String firstName, String lastName, String password, Long roleId) {
    }
}