package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("SELECT d FROM Driver d WHERE d.isDeleted = false")
    List<Driver> findAllDeleted();

    @Query("select u.username from User u join Driver d on d.email = u.email where d.driverId = :driverId ")
    String findUserNameById(@Param("driverId")Long driverId);
}
