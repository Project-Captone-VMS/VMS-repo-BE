package org.example.vmsproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "waypoints")
public class Waypoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waypointId;

    private double lat;
    private double lng;
    private int sequence;
    private String estimatedArrival;
    private String estimatedDeparture;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
}

