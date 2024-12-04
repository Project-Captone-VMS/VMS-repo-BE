package org.example.vmsproject.controller;

import org.example.vmsproject.dto.InterconnectionDTO;
import org.example.vmsproject.entity.Interconnection;
import org.example.vmsproject.service.InterconnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/interconnections")
public class InterconnectionController {
    @Autowired
    private InterconnectionService interconnectionService;

    @PutMapping("/update/{routeId}")
    public ResponseEntity<String> update(@PathVariable Long routeId, @RequestBody InterconnectionDTO interconnection) {
        String results = interconnectionService.updateTimeEstimateByDriver(routeId, interconnection);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/route/{route_id}")
    public List<Interconnection> getByRouteId(@PathVariable("route_id")  Long routeId) {
        return interconnectionService.getInterconnectionByRouteId(routeId);
    }
}
