package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private Route route;

//    @ManyToOne
//    @JoinColumn(name = "warehouse_id")
//    private Warehouse warehouse;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "shipment-items")
    private List<Item> items;
}