package org.example.vmsproject.service.Implement;

import org.example.vmsproject.entity.Vehicle;
import org.example.vmsproject.repository.VehicleRepository;
import org.example.vmsproject.service.Interface.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public String updateVehicle(long id, Vehicle vehicle) {
        return vehicleRepository.findById(id).map(vehicleUpdate ->{
            vehicleUpdate.setLicensePlate(vehicle.getLicensePlate());
            vehicleUpdate.setType(vehicle.getType());
            vehicleUpdate.setCapacity(vehicle.getCapacity());
            vehicleUpdate.setStatus(vehicle.getStatus());
            vehicleUpdate.setMaintenanceSchedule(vehicle.getMaintenanceSchedule());
            vehicleRepository.save(vehicleUpdate);
            return "Vehicle updated successfully!";
        }).orElse("Vehicle not found!");
    }

    @Override
    public String addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return "Vehicle added successfully!";
    }

    @Override
    public String deleteVehicle(long id) {
        vehicleRepository.deleteById(id);
        return "Vehicle deleted successfully!";
    }
}