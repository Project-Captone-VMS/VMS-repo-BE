package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE  v.status = false ")
    List<Vehicle> findAllVehicleNoActive();

    @Query("select count(*) as total_vehicles From Vehicle ")
    int findTotalVehicles();
}
