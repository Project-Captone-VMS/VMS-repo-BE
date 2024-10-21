package org.example.vmslogin.mapper;


import org.example.vmslogin.dto.request.PermissionRequest;
import org.example.vmslogin.dto.response.PermissionResponse;
import org.example.vmslogin.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
