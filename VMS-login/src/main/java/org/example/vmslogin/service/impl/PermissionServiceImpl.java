package org.example.vmslogin.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.vmslogin.dto.request.PermissionRequest;
import org.example.vmslogin.dto.response.PermissionResponse;
import org.example.vmslogin.entity.Permission;
import org.example.vmslogin.mapper.PermissionMapper;
import org.example.vmslogin.repository.PermissionRepository;
import org.example.vmslogin.service.PermissionService;
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
