package org.example.vmsproject.service.impl;

import jakarta.transaction.Transactional;
import org.example.vmsproject.dto.request.DriverRequest;
import org.example.vmsproject.dto.request.UpdateDriverRequest;
import org.example.vmsproject.dto.response.DriverResponse;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.User;
import org.example.vmsproject.exception.AppException;
import org.example.vmsproject.exception.ErrorCode;
import org.example.vmsproject.mapper.DriverMapper;
import org.example.vmsproject.repository.DriverRepository;
import org.example.vmsproject.repository.UserRepository;
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
    DriverMapper driverMapper;
    @Autowired
    private UserRepository userRepository;

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
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DRIVER_NOT_FOUND));

        if (!request.getLicenseNumber().equals(driver.getLicenseNumber()) &&
                driverRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new AppException(ErrorCode.LICENSE_NUMBER_EXISTS);
        }

        if (!request.getPhoneNumber().equals(driver.getPhoneNumber()) &&
                driverRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTS);
        }
        if (!request.getEmail().equals(driver.getEmail()) &&
                driverRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTS);
        }

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

            User user = userRepository.findUserByDriver(driver.getUser().getDriver());
            userRepository.deleteByUsername(user.getUsername());
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
    public Driver findAllDriverByUsername(String username) {
        return driverRepository.findByUserUsername(username);
    }

    @Transactional
    @Override
    public Driver updateInfo(Long id, UpdateDriverRequest request) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DRIVER_NOT_FOUND));

        if (!request.getLicenseNumber().equals(driver.getLicenseNumber()) &&
                driverRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new AppException(ErrorCode.LICENSE_NUMBER_EXISTS);
        }

        if (!request.getPhoneNumber().equals(driver.getPhoneNumber()) &&
                driverRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTS);
        }
        if (!request.getEmail().equals(driver.getEmail()) &&
                driverRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTS);
        }

        driver.setEmail(request.getEmail());
        driver.setLicenseNumber(request.getLicenseNumber());
        driver.setWorkSchedule(request.getWorkSchedule());
        driver.setPhoneNumber(request.getPhoneNumber());
        driverRepository.save(driver);

        Optional<User> userOptional = userRepository.findById(driver.getUser().getId());
        if (userOptional.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        User user = userOptional.get();
        if (!request.getEmail().equals(user.getEmail())) {
            user.setEmail(request.getEmail());
        }
        if (!request.getPhoneNumber().equals(user.getPhoneNumber())) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        userRepository.save(user);

        return driver;
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