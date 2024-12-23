package org.example.vmsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDTO {
    private String origin;
    private String destination;
    private double distance;
    private int duration;
    private String polyline;

}
