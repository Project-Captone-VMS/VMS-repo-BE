package org.example.vmsproject.service.Implement;

import org.example.vmsproject.dto.DriverDTO;
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
    public String updateDriver(long id, DriverDTO driverDTO) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            driver.setLicenseNumber(driverDTO.getLicenseNumber());
            driver.setWorkSchedule(driverDTO.getWorkSchedule());
            driver.setStatus(driverDTO.getStatus());
            driverRepository.save(driver);
            return "Driver updated successfully.";
        } else {
            return "Driver not found.";
        }
    }

    @Override
    public String deleteDriver(long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            driverRepository.delete(optionalDriver.get());
            return "Driver deleted successfully.";
        } else {
            return "Driver not found.";
        }
    }
}
