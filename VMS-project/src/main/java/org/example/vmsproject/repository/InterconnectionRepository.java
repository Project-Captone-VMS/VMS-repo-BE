package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Interconnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterconnectionRepository extends JpaRepository<Interconnection, Long> {
    @Query("select r from Interconnection r where r.route.routeId= :route_id")
    List<Interconnection> findByRouteId(@Param("route_id") long route_id);
}
