package org.example.vmsproject.mapper;

import org.example.vmsproject.dto.request.RoleRequest;
import org.example.vmsproject.dto.response.RoleResponse;
import org.example.vmsproject.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

}
