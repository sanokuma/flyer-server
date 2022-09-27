package com.intuit.flyer.entity;

import com.intuit.flyer.audit.Auditable;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vehicle")
@EntityListeners(AuditingEntityListener.class)
public class Vehicle extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 25)
    private int id;
    @Column(name = "vehicle_no", nullable = false, length = 15, unique = true)
    private String vehicleNo;

}
