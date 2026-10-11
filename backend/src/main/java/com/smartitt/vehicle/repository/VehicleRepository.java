package com.smartitt.vehicle.repository;

import com.smartitt.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    List<Vehicle> findAllByOrderByChassisNumberAsc();

    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);

    Optional<Vehicle> findByChassisNumber(String chassisNumber);

    boolean existsByVehicleNumber(String vehicleNumber);

    boolean existsByChassisNumber(String chassisNumber);

    List<Vehicle> findByOperator(String operator);
}
