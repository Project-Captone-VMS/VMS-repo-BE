package org.example.vmsproject.service;

import org.example.vmsproject.dto.InterconnectionDTO;

public interface InterconnectionService {
    String updateTimeEstimateByDriver(Long routeId, InterconnectionDTO interconnectionDTO);
}
