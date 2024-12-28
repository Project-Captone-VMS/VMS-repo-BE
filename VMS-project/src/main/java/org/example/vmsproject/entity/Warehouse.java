package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference(value = "warehouse-shipments")
    private List<Shipment> shipments;

    @OneToMany(mappedBy = "warehouse")
    @JsonBackReference(value = "warehouse-products")
    private List<Product> products;

    @OneToMany(mappedBy = "warehouse")
    @JsonManagedReference(value = "warehouse-items")
    private List<Item> items;



    public void addCurrentStock(int quantity) {
        if (quantity > 0) {
            this.currentStock += quantity;
        } else {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
        }
    }
}