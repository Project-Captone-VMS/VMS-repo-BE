package org.example.vmsproject.service;


import org.example.vmsproject.dto.request.PermissionRequest;
import org.example.vmsproject.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(String permission);
}
