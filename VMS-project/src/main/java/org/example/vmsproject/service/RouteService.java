package org.example.vmsproject.service;

import org.example.vmsproject.entity.Route;

public interface RouteService {
    public String findSequence(double startLat, double startLng, String destinations, double endLat, double endLng, long driverId, long vehicleId);
    public String getRoute(double startLat, double startLng, double endLat, double endLng);
    public String updateActiveRoute(long routeId);
}