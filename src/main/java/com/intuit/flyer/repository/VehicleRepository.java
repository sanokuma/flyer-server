package com.intuit.flyer.repository;

import com.intuit.flyer.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    public Vehicle findByVehicleNo(String vehicleNo);
}
