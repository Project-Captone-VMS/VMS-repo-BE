package org.example.vmsproject.service.impl;

import org.example.vmsproject.entity.Waypoint;
import org.example.vmsproject.repository.WaypointRepository;
import org.example.vmsproject.service.WaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaypointServiceImpl implements WaypointService {
    @Autowired
    WaypointRepository waypointRepository;

    @Override
    public List<Waypoint> getWaypointsByRouteId(Long routeId) {
        return waypointRepository.findByRouteId(routeId);
    }
}
