package org.example.vmsproject.service.impl;

import jakarta.transaction.Transactional;
import org.example.vmsproject.dto.WarehouseDTO;
import org.example.vmsproject.entity.Warehouse;
import org.example.vmsproject.exception.AppException;
import org.example.vmsproject.exception.ErrorCode;
import org.example.vmsproject.repository.ProductRepository;
import org.example.vmsproject.repository.WarehouseRepository;
import org.example.vmsproject.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    ProductRepository productRepository;

    @Transactional
    @Override
    public void deleteWarehouse(Long id) {
        productRepository.deleteAllByWarehouseWarehouseId(id);
        warehouseRepository.deleteById(id);
    }

    @Override
    public Warehouse updateWarehouse(long id, WarehouseDTO request) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_WAREHOUSE));
        if (!warehouse.getWarehouseName().equals(request.getWarehouseName()) ||
                !warehouse.getLocation().equals(request.getLocation())) {

            Optional<Warehouse> existingWarehouse = warehouseRepository.findByWarehouseNameAndLocation(
                    request.getWarehouseName(), request.getLocation()
            );

            if (existingWarehouse.isPresent()) {
                throw new AppException(ErrorCode.WAREHOUSE_ALREADY_EXISTS);
            }
        }
        warehouse.setWarehouseName(request.getWarehouseName());
        warehouse.setLocation(request.getLocation());
        warehouse.setCapacity(request.getCapacity());
        warehouse.setCurrentStock(request.getCurrentStock());

        warehouseRepository.save(warehouse);

        return warehouse;
    }

    @Override
    public void addWarehouse(WarehouseDTO request) {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findByWarehouseNameAndLocation(
                request.getWarehouseName(), request.getLocation()
        );
        if (existingWarehouse.isPresent()) {
            throw new AppException(ErrorCode.WAREHOUSE_ALREADY_EXISTS);
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setCapacity(request.getCapacity());
        warehouse.setLocation(request.getLocation());
        warehouse.setCurrentStock(request.getCurrentStock());
        warehouse.setWarehouseName(request.getWarehouseName());
        warehouseRepository.save(warehouse);

    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse getWarehouseById(long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Override
    public int totalWarehouse() {
        return warehouseRepository.findTotalWarehouses();
    }

    @Override
    public int totalLocation() {
        return warehouseRepository.findTotalLocations();
    }

    @Override
    public int totalOver() {
        return warehouseRepository.findTotalOvers();
    }

    @Override
    public int totalLess() {
        return warehouseRepository.findTotalLesss();
    }

}
