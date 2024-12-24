package org.example.vmsproject.controller;

import org.example.vmsproject.dto.request.CreateShipment;
import org.example.vmsproject.dto.request.ItemRequest;
import org.example.vmsproject.dto.request.UpdateShipment;
import org.example.vmsproject.entity.Shipment;
import org.example.vmsproject.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/shipment/")
@RestController
@CrossOrigin(origins = "*")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("getAll")
    public ResponseEntity<List<Shipment>> getAll() {
        List<Shipment> shipments = shipmentService.findAllShipment();
        return ResponseEntity.ok(shipments);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody CreateShipment request) {
        Shipment shipment = shipmentService.saveShipment(request);
        return ResponseEntity.ok(shipment);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UpdateShipment request) {
        Shipment shipment = shipmentService.updateShipment(id, request);
        return ResponseEntity.ok(shipment);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") Long id){
        shipmentService.deleteShipment(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
