package org.example.vmsproject.controller;

<<<<<<< HEAD
import org.example.vmsproject.dto.request.FindSequenceRequest;
import org.example.vmsproject.entity.Route;
=======
import org.example.vmsproject.dto.RouteDTO;
import org.example.vmsproject.dto.request.FindSequenceRequest;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.RouteEntity;
import org.example.vmsproject.repository.RouteEntityRepository;
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
import org.example.vmsproject.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/route")
@CrossOrigin(origins = "http://localhost:5173")
public class RouteController {
    @Autowired
    private RouteService routeService;
<<<<<<< HEAD
=======
    @Autowired
    private RouteEntityRepository routeEntityRepository;
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985

    @GetMapping("/findRoute")
    public String getRoute(
            @RequestParam double originLat,
            @RequestParam double originLng,
            @RequestParam double destinationLat,
            @RequestParam double destinationLng){
        return routeService.getRoute(originLat, originLng, destinationLat, destinationLng);
    }

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

    @GetMapping("")
    public List<Route> getAllRoutesNoActive() {
        return routeService.getAllRouteNoActive();
    }

    @GetMapping("/done")
    public List<Route> getAllRoutesActive() {
        return routeService.getAllRouteActive();
    }

    @GetMapping("/user/{username}")
    public List<Route> getAllRoutesActiveByUsername(@PathVariable("username") String username) {
        return routeService.getRouteByUserName(username);
    }
<<<<<<< HEAD

    @GetMapping("/userDone/{username}")
    public List<Route> getAllRoutesActiveByUsernameDone(@PathVariable("username") String username) {
        return routeService.getRouteByUserNameDone(username);
    }
=======
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
    @GetMapping("/search-suggestions")
    public Map<String, Object> getSearchSuggestions(
            @RequestParam("query") String query,
            @RequestParam("lat") double latitude,
            @RequestParam("lng") double longitude) {
        return routeService.getSearchSuggestions(query, latitude, longitude);
    }

<<<<<<< HEAD
    @GetMapping("/{routeId}")
    public Optional<Route> getRouteById(@PathVariable("routeId") Long routeId) {
        return routeService.getRouteByRouteId(routeId);
    }


    @GetMapping("/allRoute/{username}")
    public List<Route> listAllRouteByUsername(@PathVariable("username") String username) {
        return routeService.getAllRouteByUserName(username);
    }

    @GetMapping("/all")
    public List<Route> listAll() {
        return routeService.getAllRoute();
    }
=======
    @GetMapping("/getAll")
    public List<String> getRoutes(
            @RequestParam double originLat,
            @RequestParam double originLng,
            @RequestParam double destinationLat,
            @RequestParam double destinationLng) {
        return routeService.getMultipleRoutes(originLat, originLng, destinationLat, destinationLng);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveRoute(@RequestBody RouteDTO route) {
        routeService.saveRoute(route);
        return ResponseEntity.ok("Route saved successfully");
    }

    @GetMapping("/saved")
    public ResponseEntity<List<RouteDTO>> getSavedRoutes() {
        return ResponseEntity.ok(routeService.getSavedRoutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteEntity> getRouteById(@PathVariable Long id) {
        Optional<RouteEntity> route = routeEntityRepository.findById(id);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
}
