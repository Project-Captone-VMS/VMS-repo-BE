package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query("select count(*) as total_warehouses From Warehouse")
    int findTotalWarehouses();
    @Query("select count(*) as total_locations From Warehouse")
    int findTotalLocations();
    @Query("select count(*) as total_Overs From Warehouse WHERE capacity > 10000 ")
    int findTotalOvers();
    @Query("select count(*) as total_Lesss From Warehouse WHERE capacity < 10000 ")
    int findTotalLesss();
}
