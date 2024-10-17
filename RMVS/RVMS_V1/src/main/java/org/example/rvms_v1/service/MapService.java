package org.example.rvms_v1.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {

    private final RestTemplate restTemplate;


    private final String hereApiUrl = "https://wps.hereapi.com/v8/findsequence2";
    private final String apiKey = "https://wps.hereapi.com/v8/findsequence2?start=WiesbadenCentralStation;50.0715,8.2434&amp;destination1=FranfurtCentralStation;50.1073,8.6647&amp;destination2=DarmstadtCentralStation;49.8728,8.6326&amp;destination3=FrankfurtAirport;50.0505,8.5698&amp;destination4=HanauCentralStation;50.1218,8.9298&amp;end=MainzCentralStation;50.0021,8.259&amp;improveFor=time&amp;departure=2014-12-09T09:30:00%2b01:00&amp;mode=fastest;car;traffic:enabled?apikey=RDZ955b4FVsymS-8fDrYP5D--V1pR55u5S4dbuTNMa4";
    private final String bearerToken = "Bearer https://wps.hereapi.com/v8/findsequence2?start=WiesbadenCentralStation;50.0715,8.2434&amp;destination1=FranfurtCentralStation;50.1073,8.6647&amp;destination2=DarmstadtCentralStation;49.8728,8.6326&amp;destination3=FrankfurtAirport;50.0505,8.5698&amp;destination4=HanauCentralStation;50.1218,8.9298&amp;end=MainzCentralStation;50.0021,8.259&amp;improveFor=time&amp;departure=2014-12-09T09:30:00%2b01:00&amp;mode=fastest;car;traffic:enabled";

    @Value("${here.api.key}")
    private String hereApiKey;

    public MapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGeocode(String location) {
        String url = "https://geocode.search.hereapi.com/v1/geocode?q=" + location + "&apiKey=" + hereApiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String calculateRoute(double startLat, double startLng, double endLat, double endLng) {
        String url = String.format(
                "https://router.hereapi.com/v8/routes?transportMode=car&origin=%f,%f&destination=%f,%f&return=polyline;turnByTurnInstructions&apiKey=%s",
                startLat, startLng, endLat, endLng, hereApiKey);
        return restTemplate.getForObject(url, String.class);
    }


    public String findRouteSequence() {
        String url = "https://wps.hereapi.com/v8/findsequence2?start=WiesbadenCentralStation;50.0715,8.2434&destination1=FranfurtCentralStation;50.1073,8.6647&destination2=DarmstadtCentralStation;49.8728,8.6326&destination3=FrankfurtAirport;50.0505,8.5698&destination4=HanauCentralStation;50.1218,8.9298&end=MainzCentralStation;50.0021,8.259&improveFor=time&departure=2014-12-09T09:30:00%2b01:00&mode=fastest;car;traffic:enabled";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "https://wps.hereapi.com/v8/findsequence2?start=WiesbadenCentralStation;50.0715,8.2434&amp;destination1=FranfurtCentralStation;50.1073,8.6647&amp;destination2=DarmstadtCentralStation;49.8728,8.6326&amp;destination3=FrankfurtAirport;50.0505,8.5698&amp;destination4=HanauCentralStation;50.1218,8.9298&amp;end=MainzCentralStation;50.0021,8.259&amp;improveFor=time&amp;departure=2014-12-09T09:30:00%2b01:00&amp;mode=fastest;car;traffic:enabled");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}



