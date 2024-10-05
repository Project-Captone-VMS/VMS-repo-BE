package org.example.vmsproject.service.Interface;

import org.example.vmsproject.entity.Vehicle;

import java.util.List;

public interface IVehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(long id);
    String updateVehicle(long id, Vehicle vehicle);
    String addVehicle(Vehicle vehicle);
    String deleteVehicle(long id);
}
