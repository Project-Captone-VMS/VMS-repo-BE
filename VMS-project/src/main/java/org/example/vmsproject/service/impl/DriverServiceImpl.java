package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.DriverDTO;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.repository.DriverRepository;
import org.example.vmsproject.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAllDeleted();
    }

    @Override
    public Optional<Driver> getDriverById(long id) {
        return driverRepository.findById(id);
    }

    @Override
    public String updateDriver(long id, DriverDTO driverDTO) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            driver.setLicenseNumber(driverDTO.getLicenseNumber());
            driver.setWorkSchedule(driverDTO.getWorkSchedule());
            driver.setStatus(driverDTO.getStatus());
            driver.setEmail(driverDTO.getEmail());
            driver.setPhoneNumber(driverDTO.getPhoneNumber());
            driverRepository.save(driver);
            return "Driver updated successfully.";
        } else {
            return "Driver not found.";
        }
    }

    @Override
    public String softDeleteDriver(long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            driver.setIsDeleted(true);
            driver.setDeleteAt(LocalDateTime.now());
            driverRepository.save(driver);
            return "Driver deleted successfully.";
        } else {
            return "Driver not found.";
        }
    }
}
