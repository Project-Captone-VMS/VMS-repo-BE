package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Item;
import org.example.vmsproject.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item>findAllByWarehouseWarehouseId(Long id);
    @Query("SELECT r FROM Item r WHERE r.shipment.shipmentId = :shipment_id")
    List<Item> findAllByShipmentId(@Param("shipment_id")Long shipment_id);

    Optional<Item> findByItemNameAndShipment(String itemName, Shipment shipment);

    @Query("SELECT i FROM Item i WHERE i.shipment.shipmentId = :shipment_id AND i.itemName = :item_name")
    Optional<Item> findByShipmentIdAndItemName(@Param("shipment_id") Long shipment_id, @Param("item_name") String item_name);

}
