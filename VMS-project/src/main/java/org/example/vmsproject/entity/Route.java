package org.example.vmsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;
    private String startLocation;
    private String endLocation;
    private int distance;
    private String estimatedTime;
    private String status;

    @OneToMany(mappedBy = "route")
    private List<Shipment> shipments;
    @OneToMany(mappedBy = "route")
    private List<RouteVehicle> routeVehicles;
}
