package org.example.vmsproject.controller;

import org.example.vmsproject.dto.InterconnectionDTO;
import org.example.vmsproject.service.impl.InterconnectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/interconnections")
public class InterconnectionController {
    @Autowired
    private InterconnectionServiceImpl interconnectionService;

    @PutMapping("/update/{routeId}")
    public ResponseEntity<String> update(@PathVariable Long routeId, @RequestBody InterconnectionDTO interconnection) {
        String results = interconnectionService.updateTimeEstimateByDriver(routeId, interconnection);
        return ResponseEntity.ok(results);
    }
}
