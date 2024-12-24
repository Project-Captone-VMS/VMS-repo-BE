package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public Warehouse(Long warehouseId, String location, String warehouseName, int capacity) {
        this.warehouseId = warehouseId;
        this.location = location;
        this.warehouseName = warehouseName;
        this.capacity = capacity;
        this.currentStock = 0;
    }

    @OneToMany(mappedBy = "warehouse")
    private List<Shipment> shipments;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouse")
    private List<Product> products;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouse")
    private List<Item> items;

    public void addCurrentStock(int quantity) {
        if (quantity > 0) {
            this.currentStock += quantity;
        } else {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
        }
    }
}