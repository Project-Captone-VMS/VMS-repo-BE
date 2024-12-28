package org.example.vmsproject.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.vmsproject.dto.request.RoleRequest;
import org.example.vmsproject.dto.response.RoleResponse;
import org.example.vmsproject.mapper.RoleMapper;
import org.example.vmsproject.repository.PermissionRepository;
import org.example.vmsproject.repository.RoleRepository;
import org.example.vmsproject.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Override
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse>getAll(){
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    @Override
    public void delete(String role){
        roleRepository.deleteById(role);
    }
}
