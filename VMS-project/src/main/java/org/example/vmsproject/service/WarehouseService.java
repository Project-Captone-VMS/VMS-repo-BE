package org.example.vmsproject.service;

import org.example.vmsproject.dto.WarehouseDTO;
import org.example.vmsproject.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    List<Warehouse> getAllWarehouses();
    Warehouse getWarehouseById(long  id);
    void addWarehouse(WarehouseDTO warehouse);
    Warehouse updateWarehouse(long id ,WarehouseDTO warehouse);
    void deleteWarehouse(Long id);

    int totalWarehouse();
    int totalLocation();
    int totalOver();
    int totalLess();
}
