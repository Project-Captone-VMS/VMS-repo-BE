package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnore
    private Boolean isDeleted = false;
    @JsonIgnore
    private LocalDateTime deleteAt;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
//
//    @OneToMany(mappedBy = "driver")
//    private List<Expense>expenses;

}