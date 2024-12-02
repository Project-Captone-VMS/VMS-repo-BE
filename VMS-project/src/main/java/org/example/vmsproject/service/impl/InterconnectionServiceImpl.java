package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.InterconnectionDTO;
import org.example.vmsproject.entity.Interconnection;
import org.example.vmsproject.repository.InterconnectionRepository;
import org.example.vmsproject.service.InterconnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterconnectionServiceImpl implements InterconnectionService {
    @Autowired
    private InterconnectionRepository interconnectionRepository;

    @Override
    public String updateTimeEstimateByDriver(Long routeId, InterconnectionDTO interconnectionDTO) {
        Interconnection interconnection = new Interconnection();
        interconnection.setTimeEstimate(interconnectionDTO.getTimeEstimate());
        interconnectionRepository.save(interconnection);
        return "Updated Time Estimate By Driver Successfully ";
    }
}
