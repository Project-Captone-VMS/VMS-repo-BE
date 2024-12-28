package org.example.vmsproject.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.vmsproject.dto.IncidentDTO;
import org.example.vmsproject.entity.EIncident;
import org.example.vmsproject.entity.Incident;
import org.example.vmsproject.repository.IncidentRepository;
import org.example.vmsproject.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IncidentService {
    @Autowired
    private IncidentRepository incidentRepository;

    @Override
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    @Override
    public Incident getIncidentById(Long incidentId) {
        return incidentRepository.findById(incidentId).orElse(null);
    }

    @Override
    public String addIncident(IncidentDTO incidentDTO) {
        Incident incident = new Incident();
        incident.setDescription(incidentDTO.getDescription());
        incident.setType(incidentDTO.getType());
        incident.setOccurredAt(incidentDTO.getOccurredAt());
        incident.setDriver(incidentDTO.getDriver());
        incident.setVehicle(incidentDTO.getVehicle());
        incidentRepository.save(incident);
        return "Added new incident successfully";
    }

    @Override
    public String updateIncident(Long incidentId, IncidentDTO incidentDTO) {
        Optional<Incident> incidentOptional = incidentRepository.findById(incidentId);
        if (incidentOptional.isPresent()) {
            Incident incident = incidentOptional.get();
            incident.setDescription(incidentDTO.getDescription());
            incident.setType(incidentDTO.getType());
            incident.setOccurredAt(incidentDTO.getOccurredAt());
            incidentRepository.save(incident);
            return "Updated new incident successfully";
        }else{
            return "Incident not found";
        }
    }

    @Override
    public void deleteIncident(Long incidentId) {
        incidentRepository.deleteById(incidentId);
    }

    @Override
    public List<Incident> getIncidentsByType(EIncident type) {
        return incidentRepository.findByType(type);
    }

    @Override
    public List<Incident> getIncidentsByMonth(int month) {
        return incidentRepository.findByMonth(month);
    }

    @Override
    public List<Incident> getIncidentsByDriver(Long driverId) {
        return incidentRepository.findByDriver_DriverId(driverId);
    }

    @Override
    public List<Incident> getIncidentsByVehicle(Long vehicleId) {
        return incidentRepository.findByVehicle_VehicleId(vehicleId);
    }
}
