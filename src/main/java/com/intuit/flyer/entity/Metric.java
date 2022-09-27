package com.intuit.flyer.entity;

import com.intuit.flyer.audit.Auditable;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "metric")
@EntityListeners(AuditingEntityListener.class)
public class Metric extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, length = 25)
  private int id;
  @Column(name = "vehicle_no", nullable = false, length = 15)
  private String vehicleNo;
  @Column(name = "vehicle_speed", nullable = false, length = 15)
  private Float vehicleSpeed;
  @Column(name = "vehicle_lat", nullable = false, length = 10)
  private Float vehicleLat;
  @Column(name = "vehicle_lon", nullable = false, length = 10)
  private Float vehicleLon;
  @Column(name = "vehicle_fuel", nullable = false, length = 10)
  private Float vehicleFuel;

}
