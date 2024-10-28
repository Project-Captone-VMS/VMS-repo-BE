package org.example.vmsproject.mapper;


import org.example.vmsproject.dto.request.PermissionRequest;
import org.example.vmsproject.dto.response.PermissionResponse;
import org.example.vmsproject.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
