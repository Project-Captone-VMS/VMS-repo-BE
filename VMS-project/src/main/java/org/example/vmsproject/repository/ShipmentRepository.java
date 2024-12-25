package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Shipment findShipmentByRoute(Route route);
}
