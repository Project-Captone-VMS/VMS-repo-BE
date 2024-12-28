package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    User findUserByDriver(Driver driver);

    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);

    User findByEmail(String email);
    User findByPhoneNumber(String number);

    User deleteByUsername(String username);


}
