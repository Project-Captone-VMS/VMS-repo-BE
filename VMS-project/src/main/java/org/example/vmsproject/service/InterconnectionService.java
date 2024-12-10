package org.example.vmsproject.service;

import org.example.vmsproject.dto.InterconnectionDTO;
import org.example.vmsproject.entity.Interconnection;

import java.util.List;
import java.util.Optional;

public interface InterconnectionService {
    String updateTimeEstimateByDriver(Long interId, InterconnectionDTO interconnectionDTO);
    List<Interconnection> getInterconnectionByRouteId(Long routeId);
    String updateTimeActualByDriver(Long interId, InterconnectionDTO interconnectionDTO);
    Optional<Interconnection> getInterconnection(Long interId);
}
