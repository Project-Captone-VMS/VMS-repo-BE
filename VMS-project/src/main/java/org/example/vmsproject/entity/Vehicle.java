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
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String licensePlate;
    private String type;
    private int capacity;
    private String status;
    private String maintenanceSchedule;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToMany(mappedBy = "vehicle")
    private List<Incident> incidents;

    @OneToMany(mappedBy = "vehicle")
    private List<RouteVehicle> routeVehicles;

    @OneToMany(mappedBy = "vehicle")
    private List<Expense> expenses;

}
