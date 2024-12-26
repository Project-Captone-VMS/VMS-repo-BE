package org.example.vmsproject.service;

import org.example.vmsproject.entity.Route;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RouteService {
    String findSequence(double startLat, double startLng, String destinations, double endLat, double endLng, long driverId, long vehicleId);

    String getRoute(double startLat, double startLng, double endLat, double endLng);

    Route updateActiveRoute(long routeId);

    List<Route> getAllRouteNoActive();

    List<Route> getAllRouteActive();

    List<Route> getRouteByUserName(String username);

    Map<String, Object> getSearchSuggestions(String query, double latitude, double longitude);

    Optional<Route> getRouteByRouteId(long routeId);

    List<Route> getRouteByUserNameDone(String username);

    List<Route> getAllRouteByUserName(String username);

    Page<Route> getAllRoute(boolean status, int page);
}