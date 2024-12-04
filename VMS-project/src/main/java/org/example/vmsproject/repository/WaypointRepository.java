package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WaypointRepository extends JpaRepository<Waypoint, Long> {
    @Query("select r from Waypoint r where r.route.routeId = :route_id")
    List<Waypoint> findByRouteId(@Param("route_id") long route_id);
}
