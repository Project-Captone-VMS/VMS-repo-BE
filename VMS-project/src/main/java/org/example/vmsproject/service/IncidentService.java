package org.example.vmsproject.service;

import org.example.vmsproject.dto.IncidentDTO;
import org.example.vmsproject.entity.EIncident;
import org.example.vmsproject.entity.Incident;

import java.time.LocalDateTime;
import java.util.List;

public interface IncidentService {
    List<Incident> getAllIncidents();
    Incident getIncidentById(Long incidentId);
    String addIncident(IncidentDTO incidentDTO);
    String  updateIncident(Long incidentId, IncidentDTO incidentDTO);
    void deleteIncident(Long incidentId);
    List<Incident> getIncidentsByType(EIncident type);
    List<Incident> getIncidentsByMonth(int month);
    List<Incident> getIncidentsByDriver(Long driverId);
    List<Incident> getIncidentsByVehicle(Long vehicleId);
}
