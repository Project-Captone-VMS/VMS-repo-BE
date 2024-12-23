package org.example.vmsproject.controller;

<<<<<<< HEAD
import org.example.vmsproject.dto.InterconnectionDTO;
import org.example.vmsproject.entity.Interconnection;
import org.example.vmsproject.service.InterconnectionService;
=======
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.vmsproject.dto.InterconnectionDTO;
import org.example.vmsproject.service.impl.InterconnectionServiceImpl;
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/interconnections")
public class InterconnectionController {
    @Autowired
    private InterconnectionService interconnectionService;

    @PutMapping("/timeEstimate/{routeId}")
=======
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/interconnections")
public class InterconnectionController {
    InterconnectionServiceImpl interconnectionService;

    @PutMapping("/update/{routeId}")
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
    public ResponseEntity<String> update(@PathVariable Long routeId, @RequestBody InterconnectionDTO interconnection) {
        String results = interconnectionService.updateTimeEstimateByDriver(routeId, interconnection);
        return ResponseEntity.ok(results);
    }
<<<<<<< HEAD

    @GetMapping("/route/{route_id}")
    public List<Interconnection> getByRouteId(@PathVariable("route_id")  Long routeId) {
        return interconnectionService.getInterconnectionByRouteId(routeId);
    }

    @PutMapping("/timeActual/{routeId}")
    public ResponseEntity<String> updateTimeActual(@PathVariable Long routeId, @RequestBody InterconnectionDTO interconnection) {
        String results = interconnectionService.updateTimeActualByDriver(routeId, interconnection);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public Optional<Interconnection> getInterconnections(@PathVariable Long id) {
        return interconnectionService.getInterconnection(id);
    }
=======
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
}
