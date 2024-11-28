package org.example.vmsproject.controller;

import org.example.vmsproject.dto.request.FindSequenceRequest;
import org.example.vmsproject.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/route")
@CrossOrigin(origins = "http://localhost:5173")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping("/find-sequence")
    public ResponseEntity<?> findSequence(@RequestBody FindSequenceRequest request) {
        String result = routeService.findSequence(
                request.getStartLat(),
                request.getStartLng(),
                request.getDestinations(),
                request.getEndLat(),
                request.getEndLng(),
                request.getDriverId(),
                request.getVehicleId()
        );

        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateActive(@PathVariable("id") Long id) {
        String results = routeService.updateActiveRoute(id);
        return ResponseEntity.ok(results);
    }

}
