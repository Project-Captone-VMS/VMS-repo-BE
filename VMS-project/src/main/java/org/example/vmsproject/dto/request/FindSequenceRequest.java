package org.example.vmsproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindSequenceRequest {
    private double startLat;
    private double startLng;
    private String destinations;
    private double endLat;
    private double endLng;
    private Long driverId;
    private Long vehicleId;
}
