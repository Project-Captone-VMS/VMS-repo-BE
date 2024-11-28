package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r WHERE r.driver.driverId = :driverId AND r.vehicle.vehicleId = :vehicleId ")
    Optional<Route> findRouteByDriverAndVehicle(
            @Param("driverId") Long driverId,
            @Param("vehicleId") Long vehicleId
    );

    @Query("SELECT r FROM Route r WHERE r.status = false")
    List<Route> getAllRoutesByStatus();

    @Query("SELECT r FROM Route r WHERE r.status = true")
    List<Route> getAllRoutesDone();
}
