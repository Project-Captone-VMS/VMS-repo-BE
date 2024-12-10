package org.example.vmsproject.service;

import org.example.vmsproject.dto.InterconnectionDTO;
import org.example.vmsproject.entity.Interconnection;

import java.util.List;

public interface InterconnectionService {
    String updateTimeEstimateByDriver(Long interId, InterconnectionDTO interconnectionDTO);
    List<Interconnection> getInterconnectionByRouteId(Long routeId);
    String updateTimeActualByDriver(Long interId, InterconnectionDTO interconnectionDTO);
}
