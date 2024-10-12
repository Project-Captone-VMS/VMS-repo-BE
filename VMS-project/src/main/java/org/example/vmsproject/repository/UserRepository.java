package org.example.vmsproject.repository;

import org.example.vmsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
