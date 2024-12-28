package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.InterconnectionDTO;
import org.example.vmsproject.entity.Interconnection;
import org.example.vmsproject.repository.InterconnectionRepository;
import org.example.vmsproject.service.InterconnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterconnectionServiceImpl implements InterconnectionService {
    @Autowired
    private InterconnectionRepository interconnectionRepository;

    @Override
    public String updateTimeEstimateByDriver(Long interId, InterconnectionDTO interconnectionDTO) {
        Optional<Interconnection> interconnection = interconnectionRepository.findById(interId);
        if (interconnection.isPresent()) {
            interconnection.get().setTimeEstimate(interconnectionDTO.getTimeEstimate());
            interconnectionRepository.save(interconnection.get());
        }
        return "Updated Time Estimate By Driver Successfully ";
    }

    @Override
    public List<Interconnection> getInterconnectionByRouteId(Long routeId) {
        return interconnectionRepository.findByRouteId(routeId);
    }

    @Override
    public String updateTimeActualByDriver(Long interId, InterconnectionDTO interconnectionDTO) {
        Optional<Interconnection> interconnection = interconnectionRepository.findById(interId);
        if (interconnection.isPresent()) {
            interconnection.get().setTimeActual(interconnectionDTO.getTimeActual());
            interconnectionRepository.save(interconnection.get());
        }
        return "Updated Time Actual By Driver Successfully ";
    }

    @Override
    public Optional<Interconnection> getInterconnection(Long interId) {
        return interconnectionRepository.findById(interId);
    }
}
