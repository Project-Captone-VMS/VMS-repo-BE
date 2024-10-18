package org.example.vmsproject.service.Interface;

import org.example.vmsproject.dto.WarehouseDTO;
import org.example.vmsproject.entity.Warehouse;

import java.util.List;

public interface IWarehouseService {
    List<Warehouse> getAllWarehouses();
    Warehouse getWarehouseById(long  id);
    String addWarehouse(WarehouseDTO warehouse);
    String updateWarehouse(long id ,WarehouseDTO warehouse);
    String deleteWarehouse(long id);
}