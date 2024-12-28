package org.example.vmsproject.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.vmsproject.dto.request.PermissionRequest;
import org.example.vmsproject.dto.response.PermissionResponse;
import org.example.vmsproject.entity.Permission;
import org.example.vmsproject.mapper.PermissionMapper;
import org.example.vmsproject.repository.PermissionRepository;
import org.example.vmsproject.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        log.info("Received request: " + request);
        try {
            Permission permission = permissionMapper.toPermission(request);
            log.info("Mapped permission: " + permission);
            permission = permissionRepository.save(permission);
            log.info("Saved permission: " + permission);
            return permissionMapper.toPermissionResponse(permission);
        } catch (Exception e) {
            log.error("Error creating permission", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request data");
        }
    }


    @Override
    public List<PermissionResponse> getAll() {
        var permission = permissionRepository.findAll();
        return permission.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission){
        permissionRepository.deleteById(permission);

    }
}
