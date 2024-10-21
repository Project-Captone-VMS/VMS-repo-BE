package org.example.vmslogin.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.vmslogin.entity.Permission;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String name;
    String description;
    Set<PermissionResponse>permissions;
}
