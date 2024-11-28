package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private int totalDistance;
    private int totalTime;
    private String description;

    // Thêm thông tin điểm bắt đầu
    private double startLat;
    private double startLng;

    // Thêm thông tin điểm kết thúc
    private double endLat;
    private double endLng;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Waypoint> waypoints;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Interconnection> interconnections;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private Boolean status = Boolean.FALSE;
}
