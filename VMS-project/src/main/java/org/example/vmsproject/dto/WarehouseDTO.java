package org.example.vmsproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {
    private Long warehouseId;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @Min(value = 0, message = "Capacity must be at least 0")
    private int capacity;

    @Min(value = 0, message = "Current stock must be at least 0")
    private int currentStock;

    @NotBlank(message = "Location cannot be blank")
    private String warehouseName;
}
