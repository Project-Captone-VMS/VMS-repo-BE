package org.example.vmsproject.service;

import org.example.vmsproject.entity.Route;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RouteService {
    public String findSequence(double startLat, double startLng, String destinations, double endLat, double endLng, long driverId, long vehicleId);
    public String getRoute(double startLat, double startLng, double endLat, double endLng);
    public String updateActiveRoute(long routeId);
    List<Route> getAllRouteNoActive();
    List<Route> getAllRouteActive();
    List<Route> getRouteByUserName(String username);
    public Map<String, Object> getSearchSuggestions(String query, double latitude, double longitude);
    Optional<Route> getRouteByRouteId(long routeId);
    List<Route> getRouteByUserNameDone(String username);
    List<Route> getAllRouteByUserName(String username);
    List<Route> getAllRoute();
}