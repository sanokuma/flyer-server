package com.intuit.flyer.bean.output;

import lombok.Data;

@Data
public class MetricOutput extends ErrorOutput{

    private int id;
    private String vehicleNo;
    private Float vehicleSpeed;
    private Float vehicleLat;
    private Float vehicleLon;
    private Float vehicleFuel;

}
