package org.example.vmsproject.service.Implement;

import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.repository.DriverRepository;
import org.example.vmsproject.service.Interface.DriverIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService implements DriverIService {
    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(long id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public String updateDriver(long id, Driver driver) {
        return driverRepository.findById(id).map(driverUpdate -> {
            driverUpdate.setFirstName(driver.getFirstName());
            driverUpdate.setLastName(driver.getLastName());
            driverUpdate.setLicenseNumber(driver.getLicenseNumber());
            driverUpdate.setWorkSchedule(driver.getWorkSchedule());
            driverUpdate.setStatus(driver.getStatus());
            driverRepository.save(driverUpdate);
            return "Driver updated successfully!";
        }).orElse("Driver not found!");
    }

    @Override
    public String addDriver(Driver driver) {
        driverRepository.save(driver);
        return "Driver added successfully!";
    }

    @Override
    public String deleteDriver(long id) {
        if(driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
            return "Driver deleted successfully!";
        } else {
            return "Driver not found!";
        }
    }
}
