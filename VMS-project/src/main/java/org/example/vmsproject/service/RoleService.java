package org.example.vmsproject.service;



import org.example.vmsproject.dto.request.RoleRequest;
import org.example.vmsproject.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    public RoleResponse create(RoleRequest request);

    public List<RoleResponse> getAll();

    public void delete(String role);
}
