package org.example.vmsproject.service;

import org.example.vmsproject.dto.RouteDTO;
import org.example.vmsproject.entity.Route;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RouteService {
    String findSequence(double startLat, double startLng, String destinations, double endLat, double endLng, long driverId, long vehicleId);
    String getRoute(double startLat, double startLng, double endLat, double endLng);
    String updateActiveRoute(long routeId);
    List<Route> getAllRouteNoActive();
    List<Route> getAllRouteActive();
    List<Route> getRouteByUserName(String username);
    Map<String, Object> getSearchSuggestions(String query, double latitude, double longitude);
    List<String> getMultipleRoutes(double originLat, double originLng, double destinationLat, double destinationLng);
    void saveRoute(RouteDTO routeDTO);
    List<RouteDTO> getSavedRoutes();
}