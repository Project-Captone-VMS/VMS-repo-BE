package org.example.vmsproject.controller;

import org.example.vmsproject.entity.Waypoint;
import org.example.vmsproject.service.WaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/waypoint")
public class WaypointController {
    @Autowired
    private WaypointService waypointService;

    @GetMapping("/route/{route_id}")
    public List<Waypoint> getRouteWaypoints(@PathVariable("route_id") Long route_id) {
        return waypointService.getWaypointsByRouteId(route_id);
    }
}
