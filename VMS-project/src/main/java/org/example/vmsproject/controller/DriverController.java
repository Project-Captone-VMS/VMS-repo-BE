package org.example.vmsproject.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.vmsproject.dto.DriverDTO;
import org.example.vmsproject.entity.Driver;


import org.example.vmsproject.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
    public ResponseEntity<String> updateDriver(@PathVariable("id") long id, @Valid @RequestBody DriverDTO driverDTO) {
        String result = driverService.updateDriver(id, driverDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable("id") long id) {
        String result = driverService.softDeleteDriver(id);
        return ResponseEntity.ok(result);
    }
}
