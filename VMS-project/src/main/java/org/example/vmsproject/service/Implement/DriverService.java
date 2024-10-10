package org.example.vmsproject.service.Implement;

import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.repository.DriverRepository;
import org.example.vmsproject.service.Interface.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements IDriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> getDriverById(long id) {
        return driverRepository.findById(id);
    }

    @Override
    public String updateDriver(long id, Driver driver) {
        return driverRepository.findById(id).map(existingDriver -> {
            existingDriver.setFirstName(driver.getFirstName());
            existingDriver.setLastName(driver.getLastName());
            existingDriver.setLicenseNumber(driver.getLicenseNumber());
            existingDriver.setWorkSchedule(driver.getWorkSchedule());
            existingDriver.setStatus(driver.getStatus());
            driverRepository.save(existingDriver);
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
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
            return "Driver deleted successfully!";
        } else {
            return "Driver not found!";
        }
    }
}
