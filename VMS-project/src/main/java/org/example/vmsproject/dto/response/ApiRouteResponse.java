package org.example.vmsproject.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.example.vmsproject.entity.Interconnection;
import org.example.vmsproject.entity.Waypoint;

import javax.xml.transform.Result;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiRouteResponse {
    private List<Result> results;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private List<Waypoint> waypoints;
        private String distance;
        private String time;
        private List<Interconnection> interconnections;
        private String description;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Waypoint {
            private String id;
            private double lat;
            private double lng;
            private int sequence;
            private String estimatedArrival;
            private String estimatedDeparture;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Interconnection {
            private String fromWaypoint;
            private String toWaypoint;
            private double distance;
            private double time;
        }
    }
}
