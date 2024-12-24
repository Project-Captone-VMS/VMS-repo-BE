package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemName;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

//    @OneToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
}
