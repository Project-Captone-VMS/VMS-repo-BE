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
@Table(name="warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;
    private String location;
    private String warehouseName;
    private int capacity;
    private int currentStock;

    @OneToMany(mappedBy = "warehouse")
    private List<Shipment> shipments;
}