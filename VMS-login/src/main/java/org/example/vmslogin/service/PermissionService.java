package org.example.vmslogin.service;

import org.example.vmslogin.dto.request.PermissionRequest;
import org.example.vmslogin.dto.response.PermissionResponse;
import org.example.vmslogin.entity.Permission;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(String permission);
}
