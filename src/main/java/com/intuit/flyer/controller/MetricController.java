package com.intuit.flyer.controller;

import com.intuit.flyer.bean.output.DistanceListOutput;
import com.intuit.flyer.bean.output.MetricOutput;
import com.intuit.flyer.bean.output.VehicleListOutput;
import com.intuit.flyer.entity.Metric;
import com.intuit.flyer.entity.Vehicle;
import com.intuit.flyer.service.MetricService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/metric")
@RestController
public class MetricController {

  private static final Logger log = (Logger) LoggerFactory.getLogger(MetricController.class);

  @Autowired
  MetricService metricService;

  @PostMapping("/insert")
  @ResponseBody
  public MetricOutput insert(@RequestBody Metric input) {
    return metricService.insert(input);
  }

  @PostMapping("/distance-covered")
  @ResponseBody
  public DistanceListOutput distanceCovered(@RequestBody Vehicle input) {
    return metricService.distanceCovered(input);
  }

  @PostMapping("/overspeed")
  @ResponseBody
  public VehicleListOutput overspeed(@RequestBody Vehicle input) {
    return metricService.overspeed(input);
  }
}
