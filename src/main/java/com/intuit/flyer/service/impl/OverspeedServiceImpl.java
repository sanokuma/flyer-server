package com.intuit.flyer.service.impl;

import com.intuit.flyer.entity.Overspeed;
import com.intuit.flyer.repository.OverspeedRepository;
import com.intuit.flyer.service.OverspeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("overspeedService")
public class OverspeedServiceImpl implements OverspeedService{

  private static final Logger log = (Logger) LoggerFactory.getLogger(OverspeedServiceImpl.class);
  @Autowired
  OverspeedRepository overspeedRepository;

  @Override
  public void insert(String vehicleNo, Float vehicleSpeed) {
    Overspeed os = new Overspeed();
    os.setVehicleNo(vehicleNo);
    os.setVehicleSpeed(vehicleSpeed);
    try{
      overspeedRepository.save(os);
      log.info("Saved overspeed stats for vehicle ", vehicleNo);
    } catch (Exception e){
      log.error("Caught exception while saving overspeed stats: ", e.getMessage());
    }
  }
}

