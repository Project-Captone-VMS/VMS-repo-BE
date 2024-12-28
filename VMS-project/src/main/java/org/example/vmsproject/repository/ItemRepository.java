package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item>findAllByWarehouseWarehouseId(Long id);
    @Query("SELECT r FROM Item r WHERE r.shipment.shipmentId = :shipment_id")
    List<Item> findAllByShipmentId(@Param("shipment_id")Long shipment_id);

}
