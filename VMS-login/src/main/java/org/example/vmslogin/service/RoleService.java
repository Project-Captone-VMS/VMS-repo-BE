package org.example.vmslogin.service;

import org.example.vmslogin.dto.request.RoleRequest;
import org.example.vmslogin.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    public RoleResponse create(RoleRequest request);

    public List<RoleResponse> getAll();

    public void delete(String role);
}
