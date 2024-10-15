package org.example.vmsproject.service.Interface;

import org.example.vmsproject.dto.DriverDTO;
import org.example.vmsproject.entity.Driver;

import java.util.List;
import java.util.Optional;

public interface IDriverService {
    List<Driver> getAllDrivers();
    Optional<Driver> getDriverById(long id);
    String updateDriver(long id, DriverDTO driver);
    String softDeleteDriver(long id);
}