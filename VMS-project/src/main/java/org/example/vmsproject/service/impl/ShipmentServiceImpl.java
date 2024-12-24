package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.request.CreateShipment;
import org.example.vmsproject.dto.request.UpdateShipment;
import org.example.vmsproject.entity.Shipment;
import org.example.vmsproject.exception.AppException;
import org.example.vmsproject.exception.ErrorCode;
import org.example.vmsproject.repository.RouteRepository;
import org.example.vmsproject.repository.ShipmentRepository;
import org.example.vmsproject.repository.WarehouseRepository;
import org.example.vmsproject.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public List<Shipment> findAllShipment() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment saveShipment(CreateShipment request) {
        Shipment shipment = Shipment.builder()
                .status(request.getStatus())
                .warehouse(request.getWarehouse())
                .route(request.getRoute())
                .build();
        return shipmentRepository.save(shipment);
    }

    @Override
    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }

    @Override
    public Shipment updateShipment(Long id, UpdateShipment request) {
        Shipment shipment = shipmentRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.SHIPMENT_NOT_FOUND));
        shipment.setStatus(request.getStatus());
        shipmentRepository.save(shipment);
        return shipment;
    }

    public Optional<Shipment> findByShipment(Long id){
        return shipmentRepository.findById(id);
    }
}
