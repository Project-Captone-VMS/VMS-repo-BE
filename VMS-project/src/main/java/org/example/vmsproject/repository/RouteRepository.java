package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r WHERE r.status = false")
    List<Route> getAllRoutesByStatus();

    @Query("SELECT r FROM Route r WHERE r.status = true")
    List<Route> getAllRoutesDone();

    @Query("SELECT r " +
            "FROM Route r " +
            "JOIN r.driver d " +
            "JOIN User u ON u.email = d.email " +
            "WHERE u.username = :username " +
            "AND r.status = false")
    List<Route> findRoutesByUsername(@Param("username") String username);

    @Query("SELECT r " +
            "FROM Route r " +
            "JOIN r.driver d " +
            "JOIN User u ON u.email = d.email " +
            "WHERE u.username = :username ")
    List<Route> listAllRouteByUsername(@Param("username") String username);

    @Query("SELECT r " +
            "FROM Route r " +
            "JOIN r.driver d " +
            "JOIN User u ON u.email = d.email " +
            "WHERE u.username = :username " +
            "AND r.status = true")
    List<Route> findRoutesByUsernameDone(@Param("username") String username);

}
