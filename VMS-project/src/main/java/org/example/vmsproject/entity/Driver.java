package org.example.vmsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "driver")
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String workSchedule;
    private Boolean status = false;
    private String email;
    private String phoneNumber;
    private Boolean isDeleted = false;
    private LocalDateTime deleteAt;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;
//
//    @OneToMany(mappedBy = "driver")
//    private List<Expense>expenses;

}