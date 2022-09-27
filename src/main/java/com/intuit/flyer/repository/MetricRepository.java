package com.intuit.flyer.repository;

import com.intuit.flyer.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Integer> {
    @Query(value = "select distinct(vehicle_no) FROM metric where created_date like ?1%", nativeQuery = true)
    public List<String> getDistinctVehicles(String date);

    @Query(value = "SELECT * FROM metric where vehicle_no=?1 and created_date like ?2% ORDER BY id LIMIT 1", nativeQuery = true)
    public Metric getFirstRecordForVehicleNo(String vehicleNo, String date);

    @Query(value = "SELECT * FROM metric where vehicle_no=?1 and created_date like ?2% ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public Metric getLastRecordForVehicleNo(String vehicleNo, String date);
}
