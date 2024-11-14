package org.example.vmsproject.controller;

import jakarta.validation.Valid;
import org.example.vmsproject.dto.WarehouseDTO;
import org.example.vmsproject.entity.Warehouse;
import org.example.vmsproject.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> addWarehouse(@Valid @RequestBody WarehouseDTO warehouse) {
        String result = warehouseService.addWarehouse(warehouse);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateWarehouse(@PathVariable("id") long id,@Valid @RequestBody WarehouseDTO warehouse) {
        String result = warehouseService.updateWarehouse(id, warehouse);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable("id") long id) {
        String result = warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok(result);
    }
}
