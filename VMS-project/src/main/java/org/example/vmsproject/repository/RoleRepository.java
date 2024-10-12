package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
