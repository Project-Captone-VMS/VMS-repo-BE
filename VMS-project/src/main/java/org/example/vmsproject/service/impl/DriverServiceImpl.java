package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.request.DriverRequest;
import org.example.vmsproject.dto.response.DriverResponse;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.mapper.DriverMapper;
import org.example.vmsproject.mapper.DriverMapperImpl;
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
    @Autowired
    private DriverMapperImpl driverMapperImpl;
    @Autowired
    DriverMapper driverMapper;
    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAllDeleted();
    }

    @Override
    public Optional<Driver> getDriverById(long id) {
        return driverRepository.findById(id);
    }

    @Override
    public Driver updateDriver(Long id, DriverRequest request) {
        Optional<Driver> driverResponseOptional = driverRepository.findById(id);
        if (driverResponseOptional.isEmpty()) {
            throw new RuntimeException("Driver not found with ID: " + id);
        }

        Driver driver = driverResponseOptional.get();
        driver.setFirstName(request.getFirstName());
        driver.setLastName(request.getLastName());
        driver.setLicenseNumber(request.getLicenseNumber());
        driver.setWorkSchedule(request.getWorkSchedule());
        driver.setEmail(request.getEmail());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setStatus(request.getStatus());
        driverRepository.save(driver);
        return driver;
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

    @Override
    public String findUserNameById(long id) {
        return driverRepository.findUserNameById(id);
    }

    @Override
    public List<Driver> getAllDriversNoActive() {
        return driverRepository.findAllNoActive();
    }

    @Override
    public int totalDrivers() {
        return driverRepository.findTotalDrivers();
    }

    @Override
    public int totalOnDeliverys() {
        return driverRepository.findTotalOnDeliverys();
    }

    @Override
    public int totalAvailables() {
        return driverRepository.findTotalAvailables();
    }

    @Override
    public int totalWeeks() {
        return driverRepository.findTotalWeeks();
    }
}