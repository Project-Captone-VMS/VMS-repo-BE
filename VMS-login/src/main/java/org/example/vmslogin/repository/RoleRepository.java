package org.example.vmslogin.repository;

import org.example.vmslogin.entity.Role;
import org.example.vmslogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName (String name);
}
