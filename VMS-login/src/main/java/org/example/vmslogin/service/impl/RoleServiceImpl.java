package org.example.vmslogin.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.vmslogin.dto.request.RoleRequest;
import org.example.vmslogin.dto.response.RoleResponse;
import org.example.vmslogin.mapper.RoleMapper;
import org.example.vmslogin.repository.PermissionRepository;
import org.example.vmslogin.repository.RoleRepository;
import org.example.vmslogin.service.RoleService;
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
