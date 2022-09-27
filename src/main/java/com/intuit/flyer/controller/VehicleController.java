package com.intuit.flyer.controller;

import com.intuit.flyer.bean.output.VehicleOutput;
import com.intuit.flyer.entity.Vehicle;
import com.intuit.flyer.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/vehicle")
@RestController
public class VehicleController {

  private static final Logger log = (Logger) LoggerFactory.getLogger(VehicleController.class);

  @Autowired
  VehicleService vehicleService;

  @PostMapping("/insert")
  @ResponseBody
  public VehicleOutput insert(@RequestBody Vehicle input) {
    return vehicleService.insert(input);
  }

}
