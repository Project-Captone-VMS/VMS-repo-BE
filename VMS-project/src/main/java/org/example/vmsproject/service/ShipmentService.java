package org.example.vmsproject.service;

import org.example.vmsproject.dto.request.CreateShipment;
import org.example.vmsproject.dto.request.UpdateShipment;
import org.example.vmsproject.entity.Shipment;
import org.example.vmsproject.exception.AppException;
import org.example.vmsproject.exception.ErrorCode;

import java.util.List;

public interface ShipmentService {
    List<Shipment> findAllShipment();

    Shipment saveShipment(CreateShipment request);

    void deleteShipment(Long id);

//    Shipment updateShipment(Long id, UpdateShipment request);
}
