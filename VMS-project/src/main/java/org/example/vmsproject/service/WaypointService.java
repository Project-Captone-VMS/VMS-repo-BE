package org.example.vmsproject.service;

import org.example.vmsproject.entity.Waypoint;

import java.util.List;

public interface WaypointService {
    List<Waypoint> getWaypointsByRouteId(Long routeId);
}
