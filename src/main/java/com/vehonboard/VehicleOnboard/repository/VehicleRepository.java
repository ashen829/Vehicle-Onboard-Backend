package com.vehonboard.VehicleOnboard.repository;

import com.vehonboard.VehicleOnboard.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findByRegNo(String regNo);

}
