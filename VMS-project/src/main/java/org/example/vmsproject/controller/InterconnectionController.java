package org.example.vmsproject.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.vmsproject.dto.InterconnectionDTO;
import org.example.vmsproject.service.impl.InterconnectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/interconnections")
public class InterconnectionController {
    InterconnectionServiceImpl interconnectionService;

    @PutMapping("/update/{routeId}")
    public ResponseEntity<String> update(@PathVariable Long routeId, @RequestBody InterconnectionDTO interconnection) {
        String results = interconnectionService.updateTimeEstimateByDriver(routeId, interconnection);
        return ResponseEntity.ok(results);
    }
}
