package com.intuit.flyer.repository;

import com.intuit.flyer.entity.Overspeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OverspeedRepository extends JpaRepository<Overspeed, Integer> {

    @Query(value = "select distinct(vehicle_no) FROM overspeed where created_date like ?1%", nativeQuery = true)
    public List<String> getDistinctVehicles(String date);
}
