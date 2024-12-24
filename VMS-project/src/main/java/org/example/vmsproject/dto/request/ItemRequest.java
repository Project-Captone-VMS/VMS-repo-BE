package org.example.vmsproject.dto.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vmsproject.entity.Product;
import org.example.vmsproject.entity.Warehouse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    private Long itemId;
    private String itemName;
    private double price;
    private int quantity;
    private Warehouse warehouse;
//    private Product product;
}
