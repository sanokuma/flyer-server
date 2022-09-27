package com.intuit.flyer.service;

import com.intuit.flyer.bean.output.VehicleOutput;
import com.intuit.flyer.entity.Vehicle;

public interface VehicleService {

  public VehicleOutput insert(Vehicle input);

}
