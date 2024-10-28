package org.example.vmsproject.service;

import org.example.vmsproject.dto.VehicleDTO;
import org.example.vmsproject.entity.Vehicle;

import java.util.List;

public interface IVehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(long id);
    String updateVehicle(long id, VehicleDTO vehicle);
    String addVehicle(VehicleDTO vehicle);
    String deleteVehicle(long id);
}
