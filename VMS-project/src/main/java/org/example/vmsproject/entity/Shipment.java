package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;
    private boolean status;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
//    private String startLocation;
//    private String endLocation;


    @ManyToOne
    @JoinColumn(name = "route_id")
//    @JsonBackReference
    private Route route;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
//    @JsonBackReference
    private Warehouse warehouse;
}