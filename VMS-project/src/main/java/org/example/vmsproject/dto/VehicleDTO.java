package org.example.vmsproject.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Long vehicleId;
    @NotBlank(message = "License plate is required.")
    @Size(max = 15, message = "License plate cannot exceed 15 characters.")
    private String licensePlate;

    @NotBlank(message = "Type is required.")
    @Size(max = 30, message = "Type cannot exceed 30 characters.")
    private String type;

    @Positive(message = "Capacity must be a positive number.")
    private int capacity;

    @NotBlank(message = "Status is required.")
    private String status;

    @FutureOrPresent(message = "Maintenance schedule must be today or in the future.")
    private LocalDate maintenanceSchedule;
}
