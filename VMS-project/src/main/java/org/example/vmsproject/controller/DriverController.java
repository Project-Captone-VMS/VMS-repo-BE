package org.example.vmsproject.controller;

import jakarta.validation.Valid;
import org.example.vmsproject.dto.DriverDTO;
import org.example.vmsproject.dto.request.*;
import org.example.vmsproject.dto.response.ApiResponse;
import org.example.vmsproject.dto.response.AuthenticationResponse;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable("id") long id) {
        Optional<Driver> driver = driverService.getDriverById(id);
        return driver.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Driver> updateDriver(@PathVariable("id") long id, @RequestBody DriverRequest request) {
        Driver result = driverService.updateDriver(id, request);
        return ApiResponse.<Driver>builder()
                .result(result)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable("id") long id) {
        String result = driverService.softDeleteDriver(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findUsername/{id}")
    public ResponseEntity<String> getUserNameById(@PathVariable("id") long id) {
        String result = driverService.findUserNameById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/No-Active")
    public ResponseEntity<List<Driver>> getNoActiveDrivers() {
        List<Driver> drivers = driverService.getAllDriversNoActive();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/totalDriver")
    public ResponseEntity<?> getTotalDriver() {
        int result = driverService.totalDrivers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/totalOndelivery")
    public ResponseEntity<?> getTotalOndelivery() {
        int result = driverService.totalOnDeliverys();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/totalAvailable")
    public ResponseEntity<?> getTotalAvailable() {
        int result = driverService.totalAvailables();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/totalWeek")
    public ResponseEntity<?> getTotalWeek() {
        int result = driverService.totalWeeks();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getInfo/{username}")
    public ResponseEntity<?> getInfo(@PathVariable("username") String username) {
        Driver driver = driverService.findAllDriverByUsername(username);
        return ResponseEntity.ok(driver);
    }


    @PutMapping("/updateInfo/{id}")
    public ResponseEntity<?> updateInfo(@PathVariable("id") Long id, @RequestBody UpdateDriverRequest request) {
        Driver driver = driverService.updateInfo(id, request);
        return ResponseEntity.ok(driver);
    }

}
