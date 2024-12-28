package org.example.vmsproject.controller;

import jakarta.validation.Valid;
import org.example.vmsproject.dto.WarehouseDTO;
import org.example.vmsproject.entity.Warehouse;
import org.example.vmsproject.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable("id") Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        return ResponseEntity.ok(warehouse);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addWarehouse(@Valid @RequestBody WarehouseDTO warehouse) {
        warehouseService.addWarehouse(warehouse);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable("id") long id,@Valid @RequestBody WarehouseDTO warehouse) {
          warehouseService.updateWarehouse(id, warehouse);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable("id") Long id) {
         warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/totalWarehouse")
    public ResponseEntity<?> getTotalWarehouse() {
        int result = warehouseService.totalWarehouse();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/totalLocation")
    public ResponseEntity<?> getTotalLocation() {
        int result = warehouseService.totalLocation();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/totalOver")
    public ResponseEntity<?> getTotalOver() {
        int result = warehouseService.totalOver();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/totalLess")
    public ResponseEntity<?> getTotalLess() {
        int result = warehouseService.totalLess();
        return ResponseEntity.ok(result);
    }


}
