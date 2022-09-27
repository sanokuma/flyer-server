package com.intuit.flyer.bean.output;

import com.intuit.flyer.entity.Vehicle;
import lombok.Data;

import java.util.List;

@Data
public class VehicleListOutput extends ErrorOutput{

    private List<Vehicle> vehicles;

}
