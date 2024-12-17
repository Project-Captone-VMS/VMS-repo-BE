package org.example.vmsproject.repository;

import org.example.vmsproject.entity.EIncident;
import org.example.vmsproject.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    @Query("SELECT d FROM Incident d WHERE d.type = :type")
    List<Incident> findByType(@Param("type") EIncident type);

    @Query("SELECT d FROM Incident d WHERE FUNCTION('MONTH', d.occurredAt) = :month")
    List<Incident> findByMonth(@Param("month") int month);

    @Query("SELECT d FROM Incident d WHERE d.driver.driverId = :driverid")
    List<Incident> findByDriver_DriverId(@Param("driverid") Long driverId);

    @Query("SELECT d FROM Incident d WHERE d.vehicle.vehicleId = :vehicleid")
    List<Incident> findByVehicle_VehicleId(@Param("vehicleid") Long vehicleId);
}
