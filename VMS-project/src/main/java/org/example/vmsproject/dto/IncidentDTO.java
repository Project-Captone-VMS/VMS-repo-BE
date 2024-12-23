package org.example.vmsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.EIncident;
import org.example.vmsproject.entity.Vehicle;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentDTO {
    private String description;
    private EIncident type;
    private LocalDateTime occurredAt;
    private Driver driver;
    private Vehicle vehicle;
}
