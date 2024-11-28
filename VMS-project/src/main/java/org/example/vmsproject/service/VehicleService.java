package org.example.vmsproject.service;

import org.example.vmsproject.dto.VehicleDTO;
import org.example.vmsproject.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Optional<Vehicle> getVehicleById(long id);
    String updateVehicle(long id, VehicleDTO vehicle);
    String addVehicle(VehicleDTO vehicle);
    String deleteVehicle(long id);
}
