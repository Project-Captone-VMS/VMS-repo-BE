package org.example.vmsproject.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vmsproject.dto.response.ApiRouteResponse;
import org.example.vmsproject.entity.*;
        import org.example.vmsproject.exception.AppException;
import org.example.vmsproject.exception.ErrorCode;
import org.example.vmsproject.repository.*;
        import org.example.vmsproject.service.DriverService;
import org.example.vmsproject.service.RouteService;
import org.example.vmsproject.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private WaypointRepository waypointRepository;

    @Autowired
    private InterconnectionRepository interconnectionRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DriverService driverService;

    @Autowired
    private VehicleService vehicleService;


    @Value("${here.api.key}")
    private String apiKey;


    @Override
    public String findSequence(double startLat, double startLng, String destinations, double endLat, double endLng, long driverId, long vehicleId) {
        ZonedDateTime dataTime = ZonedDateTime.now(ZoneOffset.UTC);
        String departure = dataTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));

        //Xử lý để tạo lộ trình đi bằng api của HereMap thông qua URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://wps.hereapi.com/v8/findsequence2")
                .queryParam("start", startLat + "," + startLng)
                .queryParam("end", endLat + "," + endLng)
                .queryParam("improveFor", "time")
                .queryParam("departure", departure)
                .queryParam("mode", "fastest;car;traffic:enabled")
                .queryParam("apikey", apiKey);

        // Xử lý chuỗi destinations và thêm vào builder
        if (destinations != null && !destinations.isEmpty()) {
            String[] destinationArray = destinations.split(",");

            // Kiểm tra để đảm bảo số lượng phần tử chẵn
            if (destinationArray.length % 2 == 0) {
                for (int i = 0; i < destinationArray.length; i += 2) {
                    int destinationIndex = (i / 2) + 1;
                    double lat = Double.parseDouble(destinationArray[i]);
                    double lng = Double.parseDouble(destinationArray[i + 1]);
                    builder.queryParam("destination" + destinationIndex, lat + "," + lng);
                }
            }
        }
        String url = builder.toUriString();
        System.out.println("url: " + url);

        String jsonRespone = restTemplate.getForObject(url, String.class);



        try {
            creatRoute(jsonRespone,driverId,vehicleId);
            return "Create Route Successfully";
        } catch (Exception e) {
            return "Create Route Failed " + e.getMessage();
        }

    }

    public String getAddressFromCoordinates(double lat, double lng) {
        String url = "https://revgeocode.search.hereapi.com/v1/revgeocode";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("at", lat + "," + lng)
                .queryParam("lang", "en-US")
                .queryParam("apiKey", apiKey);

        try {
            RestTemplate restTemplate = new RestTemplate();
            Map response = restTemplate.getForObject(uriBuilder.toUriString(), Map.class);

            if (response != null && response.containsKey("items")) {
                var items = (Iterable<Map<String, Object>>) response.get("items");
                if (items.iterator().hasNext()) {
                    Map<String, Object> firstItem = items.iterator().next();
                    return (String) firstItem.getOrDefault("title", "");
                }
            }
        } catch (Exception e) {
            System.out.println("Reverse geocoding error: " + e.getMessage());
        }
        return null;
    }



    private void creatRoute(String jsonRespone, Long driverId, Long vehicleId) throws JsonProcessingException {
        // Parse JSON thành đối tượng Java
        ObjectMapper mapper = new ObjectMapper();
        ApiRouteResponse apiRouteResponse = mapper.readValue(jsonRespone, ApiRouteResponse.class);

        for (ApiRouteResponse.Result result : apiRouteResponse.getResults()) {
            Route route = new Route();
            route.setTotalDistance(Integer.parseInt(result.getDistance()));
            route.setTotalTime(Integer.parseInt(result.getTime()));
            route.setDescription(result.getDescription());
            route.setStatus(false);

            //Lưu vị trí điểm bắt đầu và kết thúc
            List<ApiRouteResponse.Result.Waypoint> waypoints = result.getWaypoints();
            route.setStartLat(waypoints.get(0).getLat());
            route.setStartLng(waypoints.get(0).getLng());
            route.setEndLat(waypoints.get(waypoints.size() - 1).getLat());
            route.setEndLng(waypoints.get(waypoints.size() - 1).getLng());
            String startLocationName = getAddressFromCoordinates(
                    waypoints.get(0).getLat(),
                    waypoints.get(0).getLng()
            );
            route.setStartLocationName(startLocationName);

            String endLocationName = getAddressFromCoordinates(
                    waypoints.get(waypoints.size() - 1).getLat(),
                    waypoints.get(waypoints.size() - 1).getLng()
            );
            route.setEndLocationName(endLocationName);

            Optional<Driver> driver = driverService.getDriverById(driverId);
            Optional<Vehicle> vehicle = vehicleService.getVehicleById(vehicleId);

            //Lưu tài xế đảm nhiệm và thông tin xe vận chuyển
            if(driver.isPresent() && vehicle.isPresent()) {
                route.setDriver(driver.get());
                route.setVehicle(vehicle.get());
                driver.get().setStatus(true);
                vehicle.get().setStatus(true);
            }
            Route savedRoute = routeRepository.save(route);


            //Lưu chi tiết waypoints
            List<Waypoint> waypointsEntities = new ArrayList<>();
            for(ApiRouteResponse.Result.Waypoint waypoint : waypoints) {
                Waypoint waypointEntity = new Waypoint();
                waypointEntity.setLat(waypoint.getLat());
                waypointEntity.setLng(waypoint.getLng());
                waypointEntity.setSequence(waypoint.getSequence());
                waypointEntity.setEstimatedArrival(waypoint.getEstimatedArrival());
                waypointEntity.setEstimatedDeparture(waypoint.getEstimatedDeparture());
                waypointEntity.setRoute(savedRoute);
                String locationName = getAddressFromCoordinates(waypoint.getLat(), waypoint.getLng());
                waypointEntity.setLocationName(locationName);
                waypointsEntities.add(waypointEntity);
            }
            waypointRepository.saveAll(waypointsEntities);

            //Lưu Interconnections
            List<Interconnection> interconnectionEntities = new ArrayList<>();
            for(ApiRouteResponse.Result.Interconnection interconnection : result.getInterconnections()) {
                Interconnection interconnectionEntity = new Interconnection();
                interconnectionEntity.setFromWaypoint(interconnection.getFromWaypoint());
                interconnectionEntity.setToWaypoint(interconnection.getToWaypoint());
                interconnectionEntity.setDistance(interconnection.getDistance());
                interconnectionEntity.setTimeWaypoint(interconnection.getTime());
                interconnectionEntity.setRoute(savedRoute);

                interconnectionEntities.add(interconnectionEntity);
            }
            interconnectionRepository.saveAll(interconnectionEntities);
        }
    }

    @Override
    public String getRoute(double startLat, double startLng, double endLat, double endLng) {
        URI url = UriComponentsBuilder.fromHttpUrl("https://router.hereapi.com/v8/routes")
                .queryParam("transportMode", "car")
                .queryParam("origin", startLat + "," + startLng)
                .queryParam("destination", endLat + "," + endLng)
                .queryParam("return", "summary,actions,polyline")
                .queryParam("apiKey", apiKey)
                .build()
                .toUri();

        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public Route updateActiveRoute(long routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow(()-> new AppException(ErrorCode.ROUTE_NOT_FOUND));
        route.setStatus(true);
        route.getDriver().setStatus(false);
        route.getVehicle().setStatus(false);
        routeRepository.save(route);
        Shipment shipment = shipmentRepository.findShipmentByRoute(route);
        shipment.setStatus(true);
        shipmentRepository.save(shipment);
        return route;

    }

    @Override
    public List<Route> getAllRouteNoActive() {
        return routeRepository.getAllRoutesByStatus();
    }

    @Override
    public List<Route> getAllRouteActive() {
        return routeRepository.getAllRoutesDone();
    }

    @Override
    public List<Route> getRouteByUserName(String username) {
        return routeRepository.findRoutesByUsername(username);
    }

    @Override
    public Map<String, Object> getSearchSuggestions(String query, double latitude, double longitude) {
        String url = UriComponentsBuilder.fromHttpUrl("https://autosuggest.search.hereapi.com/v1/autosuggest")
                .queryParam("at", latitude + "," + longitude)
                .queryParam("lang", "vi-VN")
                .queryParam("limit", 5)
                .queryParam("q", query)
                .queryParam("apiKey", apiKey)
                .queryParam("country", "VN")
                .toUriString();
        return restTemplate.getForObject(url, Map.class);
    }

    @Override
    public Optional<Route> getRouteByRouteId(long routeId) {
        return routeRepository.findById(routeId);
    }

    @Override
    public List<Route> getRouteByUserNameDone(String username) {
        return routeRepository.findRoutesByUsernameDone(username);
    }

    @Override
    public List<Route> getAllRouteByUserName(String username) {
        return routeRepository.listAllRouteByUsername(username);
    }

    @Override
    public Page<Route> getAllRoute(boolean status, int page) {
        int size = 3;
        Pageable pageable = PageRequest.of(page, size);
        return routeRepository.findByStatus(status, pageable);
    }
}
