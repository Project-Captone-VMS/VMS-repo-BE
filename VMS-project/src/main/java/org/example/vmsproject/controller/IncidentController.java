package org.example.vmsproject.controller;

import org.example.vmsproject.dto.IncidentDTO;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.EIncident;
import org.example.vmsproject.entity.Incident;
import org.example.vmsproject.entity.Vehicle;
import org.example.vmsproject.service.DriverService;
import org.example.vmsproject.service.IncidentService;
import org.example.vmsproject.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    @GetMapping("/{incidentId}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long incidentId) {
        return ResponseEntity.ok(incidentService.getIncidentById(incidentId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addIncident(@RequestBody IncidentDTO incidentDTO) {
        String savedIncident = incidentService.addIncident(incidentDTO);
        return ResponseEntity.ok(savedIncident);
    }

    @PutMapping("/{incidentId}")
    public ResponseEntity<?> updateIncident(
            @PathVariable Long incidentId,
            @RequestBody IncidentDTO updatedIncident) {
        return ResponseEntity.ok(incidentService.updateIncident(incidentId, updatedIncident));
    }

    @DeleteMapping("/{incidentId}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long incidentId) {
        incidentService.deleteIncident(incidentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Incident>> getIncidentsByType(@PathVariable EIncident type) {
        List<Incident> incidents = incidentService.getIncidentsByType(type);

        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<Incident>> getIncidentsByMonth(@PathVariable int month) {
        List<Incident> incidents = incidentService.getIncidentsByMonth(month);
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Incident>> getIncidentsByDriver(@PathVariable Long driverId) {
        List<Incident> incidents = incidentService.getIncidentsByDriver(driverId);
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Incident>> getIncidentsByVehicle(@PathVariable Long vehicleId) {
        List<Incident> incidents = incidentService.getIncidentsByVehicle(vehicleId);
        return ResponseEntity.ok(incidents);
    }
}
