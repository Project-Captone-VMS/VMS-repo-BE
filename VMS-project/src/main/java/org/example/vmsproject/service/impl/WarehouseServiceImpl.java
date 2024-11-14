package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.WarehouseDTO;
import org.example.vmsproject.entity.Warehouse;
import org.example.vmsproject.repository.WarehouseRepository;
import org.example.vmsproject.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public String deleteWarehouse(long id) {
        warehouseRepository.deleteById(id);
        return "Warehouse deleted successfully";
    }

    @Override
    public String updateWarehouse(long id, WarehouseDTO warehouse) {
        return warehouseRepository.findById(id).map(warehouseUpdate ->{
            warehouseUpdate.setCapacity(warehouse.getCapacity());
            warehouseUpdate.setLocation(warehouse.getLocation());
            warehouseUpdate.setCurrentStock(warehouse.getCurrentStock());
            warehouseUpdate.setWarehouseName(warehouse.getWarehouseName());
            warehouseRepository.save(warehouseUpdate);
            return "Warehouse updated successfully";
        }).orElse("Warehouse not found");
    }

    @Override
    public String addWarehouse(WarehouseDTO warehouse) {
        Warehouse warehouseAdd = new Warehouse();
        warehouseAdd.setCapacity(warehouse.getCapacity());
        warehouseAdd.setLocation(warehouse.getLocation());
        warehouseAdd.setCurrentStock(warehouse.getCurrentStock());
        warehouseAdd.setWarehouseName(warehouse.getWarehouseName());
        warehouseRepository.save(warehouseAdd);
        return "Warehouse added successfully";
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse getWarehouseById(long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

}
