package org.example.vmslogin.mapper;

import org.example.vmslogin.dto.request.RoleRequest;
import org.example.vmslogin.dto.response.RoleResponse;
import org.example.vmslogin.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

}
