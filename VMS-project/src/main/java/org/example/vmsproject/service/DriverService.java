package org.example.vmsproject.service;

import org.example.vmsproject.dto.DriverDTO;
import org.example.vmsproject.entity.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    List<Driver> getAllDrivers();
    Optional<Driver> getDriverById(long id);
    String updateDriver(long id, DriverDTO driver);
    String softDeleteDriver(long id);
}