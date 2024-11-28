package org.example.vmsproject.entity;

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
@Table(name = "interconnections")
public class Interconnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interconnectionId;

    private String fromWaypoint;
    private String toWaypoint;
    private double distance;
    private double time;
    private double rest;
    private double waiting;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "route_id")
    private Route route;
}
