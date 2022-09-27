package com.intuit.flyer.service.impl;

import com.intuit.flyer.bean.output.VehicleOutput;
import com.intuit.flyer.entity.Vehicle;
import com.intuit.flyer.repository.VehicleRepository;
import com.intuit.flyer.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

  private static final Logger log = (Logger) LoggerFactory.getLogger(VehicleServiceImpl.class);

  @Autowired
  VehicleRepository vehicleRepository;

  @Override
  public VehicleOutput insert(Vehicle input) {
    VehicleOutput output = new VehicleOutput();
    if(input == null){
      output.setErrorMessage("Invalid input");
      return output;
    }
    try{
      Vehicle v = vehicleRepository.save(input);
      BeanUtils.copyProperties(v, output);
    } catch (Exception e){
      log.error("Exception while inserting a new vehicle", e.getMessage());
      output.setErrorMessage(e.getMessage());
      return output;
    }
    output.setSuccess(true);
    return output;
  }
}

