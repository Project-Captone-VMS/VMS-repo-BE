package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String workSchedule;
    private String status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "driver")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "driver")
    private List<Shipment>shipments;

    @OneToMany(mappedBy = "driver")
    private List<Expense>expenses;
}
