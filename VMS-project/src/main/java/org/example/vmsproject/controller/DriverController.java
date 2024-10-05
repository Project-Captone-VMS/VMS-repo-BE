package org.example.vmsproject.controller;

import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.service.Interface.DriverIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverIService driverService;

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable("id") long id) {
        Driver driver = driverService.getDriverById(id);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDriver(@RequestBody Driver driver) {
        String result = driverService.addDriver(driver);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDriver(@PathVariable("id") long id, @RequestBody Driver driver) {
        String result = driverService.updateDriver(id, driver);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable("id") long id) {
        String result = driverService.deleteDriver(id);
        return ResponseEntity.ok(result);
    }
}
