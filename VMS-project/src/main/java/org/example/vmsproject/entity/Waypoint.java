package org.example.vmsproject.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @JoinColumn(name = "route_id")
    private Route route;
}

