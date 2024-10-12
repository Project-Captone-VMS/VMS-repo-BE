package org.example.vmsproject.service.Implement;

import org.example.vmsproject.entity.Role;
import org.example.vmsproject.repository.RoleRepository;
import org.example.vmsproject.service.Interface.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
