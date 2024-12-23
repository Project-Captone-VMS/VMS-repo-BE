package org.example.vmsproject.service;

<<<<<<< HEAD
=======
import org.example.vmsproject.dto.RouteDTO;
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
import org.example.vmsproject.entity.Route;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RouteService {
<<<<<<< HEAD
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
=======
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
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
}