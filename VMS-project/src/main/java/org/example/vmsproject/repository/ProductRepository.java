package org.example.vmsproject.repository;


import org.example.vmsproject.entity.Product;
import org.example.vmsproject.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.warehouse.warehouseId = :warehouseId")
    List<Product> findAllByWarehouseId(@Param("warehouseId") Long warehouseId);
    @Query("select count(*) as total_products From Product ")
    int findTotalProducts();
    Optional<Product> findByProductNameAndWarehouse(String productName, Warehouse warehouse);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.warehouse.warehouseId = ?1")
    void deleteAllByWarehouseWarehouseId(Long warehouseId);

}
