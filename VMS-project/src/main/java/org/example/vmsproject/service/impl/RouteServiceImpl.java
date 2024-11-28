package org.example.vmsproject.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vmsproject.dto.response.ApiRouteResponse;
import org.example.vmsproject.entity.*;
import org.example.vmsproject.repository.InterconnectionRepository;
import org.example.vmsproject.repository.RouteRepository;
import org.example.vmsproject.repository.WaypointRepository;
import org.example.vmsproject.service.DriverService;
import org.example.vmsproject.service.RouteService;
import org.example.vmsproject.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private WaypointRepository waypointRepository;

    @Autowired
    private InterconnectionRepository interconnectionRepository;

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


        Optional<Route> existingRoute = routeRepository.findRouteByDriverAndVehicle(driverId, vehicleId);
        if (existingRoute.isPresent()) {
            if(existingRoute.get().getStatus()){
                try {
                    creatRoute(jsonRespone,existingRoute.get().getDriver().getDriverId(),existingRoute.get().getVehicle().getVehicleId());
                    return "Create Route Successfully";
                } catch (Exception e) {
                    return "Create Route Failed In Set Status " + e.getMessage();
                }

            }else{
                return "Route is not completed. Cannot create new.";
            }
        }else{
            try {
                creatRoute(jsonRespone,driverId,vehicleId);
                return "Create Route Successfully";
            } catch (Exception e) {
                return "Create Route Failed " + e.getMessage();
            }
        }
    }

    private void creatRoute(String jsonRespone, Long vehicleId, Long driverId) throws JsonProcessingException {
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

            Optional<Driver> driver = driverService.getDriverById(driverId);
            Optional<Vehicle> vehicle = vehicleService.getVehicleById(vehicleId);

            //Lưu tài xế đảm nhiệm và thông tin xe vận chuyển
            if(driver.isPresent() && vehicle.isPresent()) {
                route.setDriver(driver.get());
                route.setVehicle(vehicle.get());
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
                interconnectionEntity.setTime(interconnection.getTime());
                interconnectionEntity.setRoute(savedRoute);

                interconnectionEntities.add(interconnectionEntity);
            }
            interconnectionRepository.saveAll(interconnectionEntities);
        }
    }

    @Override
    public String getRoute(double startLat, double startLng, double endLat, double endLng) {
        return "";
    }

    @Override
    public String updateActiveRoute(long routeId) {
        return routeRepository.findById(routeId).map(route -> {
            route.setStatus(true);
            routeRepository.save(route);
            return "Update Active in Route Successfully";
        }).orElse("Update Not Active in Route Successfully");
    }

    @Override
    public List<Route> getAllRouteNoActive() {
        return routeRepository.getAllRoutesByStatus();
    }

    @Override
    public List<Route> getAllRouteActive() {
        return routeRepository.getAllRoutesDone();
    }
}
