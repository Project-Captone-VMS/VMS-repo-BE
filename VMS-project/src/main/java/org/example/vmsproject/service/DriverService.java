package org.example.vmsproject.service;

import org.example.vmsproject.dto.DriverDTO;
import org.example.vmsproject.dto.request.DriverRequest;
import org.example.vmsproject.dto.request.UpdateDriverRequest;
import org.example.vmsproject.entity.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    List<Driver> getAllDrivers();
    Optional<Driver> getDriverById(long id);
    Driver updateDriver(Long id, DriverRequest request);
    String softDeleteDriver(long id);
    String findUserNameById(long id);
    List<Driver> getAllDriversNoActive();
    Driver findAllDriverByUsername(String username);
    Driver updateInfo(Long id, UpdateDriverRequest request);

    int totalDrivers();
    int totalOnDeliverys();
    int totalAvailables();
    int totalWeeks();

}