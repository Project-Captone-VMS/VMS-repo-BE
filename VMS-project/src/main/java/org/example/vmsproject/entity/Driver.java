package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;

    @Pattern(regexp = "[A-Z0-9]{5,}", message = "License number must be alphanumeric and at least 5 characters")
    private String licenseNumber;

    @NotBlank(message = "Work schedule is required")
    private String workSchedule;

    @NotBlank(message = "Status is required")
    private String status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "driver")
    @JsonManagedReference
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "driver")
    private List<Shipment>shipments;

    @OneToMany(mappedBy = "driver")
    private List<Expense>expenses;
}
