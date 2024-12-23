package org.example.vmsproject.repository;

import org.example.vmsproject.dto.response.DriverResponse;
import org.example.vmsproject.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findAllByDriverId(Long id);

    @Query("SELECT d FROM Driver d WHERE d.isDeleted = false")
    List<Driver> findAllDeleted();

    @Query("select u.username from User u join Driver d on d.email = u.email where d.driverId = :driverId ")
    String findUserNameById(@Param("driverId")Long driverId);

    @Query("SELECT d FROM Driver d WHERE d.isDeleted = false and d.status = false ")
    List<Driver> findAllNoActive();

    @Query("select count(*) as total_drivers From Driver d where d.isDeleted = false ")
    int findTotalDrivers();

    @Query("select count(d) from Driver d where d.isDeleted = false and d.status = true ")
    int findTotalOnDeliverys();

    @Query("select count(d) from Driver d where d.isDeleted = false and d.status = false ")
    int findTotalAvailables();

    @Query("select count(d) from Driver d where d.isDeleted = false and d.workSchedule = 'Monday-Friday'")
    int findTotalWeeks();

    Driver findAllByUserUsername(String username);
}
