package org.example.vmsproject.controller;

import jakarta.validation.Valid;
import org.example.vmsproject.dto.VehicleDTO;
import org.example.vmsproject.entity.Vehicle;
import org.example.vmsproject.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin(origins = "http://localhost:5173")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllDrivers() {
        List<Vehicle> vehicle = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addVehicle(@Valid @RequestBody VehicleDTO vehicle) {
        String result = vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDriver(@PathVariable("id") long id,@Valid @RequestBody VehicleDTO vehicle) {
        String result = vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable("id") long id) {
        String result = vehicleService.deleteVehicle(id);
        return ResponseEntity.ok(result);
    }
}
