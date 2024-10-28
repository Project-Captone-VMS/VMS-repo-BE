package org.example.vmsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private LocalDate maintenanceSchedule;


    @OneToMany(mappedBy = "vehicle")
    private List<Incident> incidents;

    @OneToMany(mappedBy = "vehicle")
    private List<Expense> expenses;


}

