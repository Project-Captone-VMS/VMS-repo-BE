package org.example.vmsproject.controller;

import org.example.vmsproject.dto.request.FindSequenceRequest;
import org.example.vmsproject.entity.Route;
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
    public ResponseEntity<?> updateActive(@PathVariable("id") Long id) {
        Route results = routeService.updateActiveRoute(id);
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

    @GetMapping("/userDone/{username}")
    public List<Route> getAllRoutesActiveByUsernameDone(@PathVariable("username") String username) {
        return routeService.getRouteByUserNameDone(username);
    }
    @GetMapping("/search-suggestions")
    public Map<String, Object> getSearchSuggestions(
            @RequestParam("query") String query,
            @RequestParam("lat") double latitude,
            @RequestParam("lng") double longitude) {
        return routeService.getSearchSuggestions(query, latitude, longitude);
    }

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
}
