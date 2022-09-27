package com.intuit.flyer.service;

import com.intuit.flyer.bean.output.DistanceListOutput;
import com.intuit.flyer.bean.output.MetricOutput;
import com.intuit.flyer.bean.output.VehicleListOutput;
import com.intuit.flyer.entity.Metric;
import com.intuit.flyer.entity.Vehicle;

public interface MetricService {
  public MetricOutput insert(Metric input);
  public DistanceListOutput distanceCovered(Vehicle input);
  public VehicleListOutput overspeed(Vehicle input);
}
