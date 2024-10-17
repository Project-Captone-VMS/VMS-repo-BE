package org.example.rvms_v1.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.rvms_v1.service.MapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MapController {

    private final MapService mapService;
    private final RestTemplate restTemplate;

    @Value("${here.api.key}")
    private String hereApiKey;

    public MapController(MapService mapService, RestTemplate restTemplate) {
        this.mapService = mapService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/geocode")
    public ResponseEntity<String> getGeocode(@RequestParam String location) {
        String response = mapService.getGeocode(location);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/route")
    public ResponseEntity<String> calculateRoute(
            @RequestParam double startLat,
            @RequestParam double startLng,
            @RequestParam double endLat,
            @RequestParam double endLng) {
        String response = mapService.calculateRoute(startLat, startLng, endLat, endLng);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/here-api-key")
    public String getHereApiKey() {
        return hereApiKey;
    }

    @GetMapping("/route-sequence")
    public ResponseEntity<String> getRouteSequence() {
        try {
            String response = mapService.findRouteSequence();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving route sequence: " + e.getMessage());
        }
    }

    @GetMapping("/getRoute1")
    public ResponseEntity<Object> getRoute(
            @RequestParam double startLat,
            @RequestParam double startLng,
            @RequestParam double endLat,
            @RequestParam double endLng) {

        try {
            String url = String.format(
                    "https://router.hereapi.com/v8/routes?transportMode=car&origin=%f,%f&destination=%f,%f&return=summary,polyline&apikey=%s",
                    startLat, startLng, endLat, endLng, hereApiKey
            );

            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

            // In ra phản hồi để kiểm tra
            System.out.println("Response from HERE API: " + response.getBody());

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving route: " + e.getMessage());
        }
    }

}
