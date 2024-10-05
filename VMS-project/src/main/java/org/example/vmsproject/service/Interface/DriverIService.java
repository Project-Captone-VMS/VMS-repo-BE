package org.example.vmsproject.service.Interface;

import org.example.vmsproject.entity.Driver;

import java.util.List;

public interface DriverIService {
    List<Driver> getAllDrivers();
    Driver getDriverById(long id);
    String updateDriver(long id, Driver driver);
    String addDriver(Driver driver);
    String deleteDriver(long id);
}
