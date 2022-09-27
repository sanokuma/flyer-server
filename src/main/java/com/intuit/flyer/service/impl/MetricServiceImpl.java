package com.intuit.flyer.service.impl;

import com.intuit.flyer.bean.output.DistanceListOutput;
import com.intuit.flyer.bean.output.DistanceOutput;
import com.intuit.flyer.bean.output.MetricOutput;
import com.intuit.flyer.bean.output.VehicleListOutput;
import com.intuit.flyer.common.FlyerUtils;
import com.intuit.flyer.entity.Metric;
import com.intuit.flyer.entity.Vehicle;
import com.intuit.flyer.repository.MetricRepository;
import com.intuit.flyer.repository.OverspeedRepository;
import com.intuit.flyer.repository.VehicleRepository;
import com.intuit.flyer.service.MetricService;
import com.intuit.flyer.service.OverspeedService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("metricService")
public class MetricServiceImpl implements MetricService {

  private static final Logger log = (Logger) LoggerFactory.getLogger(MetricServiceImpl.class);
  @Autowired
  MetricRepository metricRepository;

  @Autowired
  VehicleRepository vehicleRepository;

  @Autowired
  private KieContainer kieContainer;

  @Autowired
  OverspeedService overspeedService;

  @Autowired
  OverspeedRepository overspeedRepository;

  @Override
  public MetricOutput insert(Metric input) {
    MetricOutput output = new MetricOutput();
    if(input == null){
      output.setErrorMessage("Invalid input");
      return output;
    }
    Vehicle v = vehicleRepository.findByVehicleNo(input.getVehicleNo());
    if(v == null){
      output.setErrorMessage("Specified vehicle does not exist");
      return output;
    }
    try{
      Metric m = metricRepository.save(input);
      BeanUtils.copyProperties(m, output);
    } catch (Exception e){
      log.error("Caught exception while saving metric", e.getMessage());
      output.setErrorMessage(e.getMessage());
      return output;
    }
    output.setSuccess(true);

    //Trigger rule
    KieSession kieSession = kieContainer.newKieSession();
    kieSession.setGlobal("overspeedService", overspeedService);
    kieSession.insert(input);
    kieSession.fireAllRules();
    kieSession.dispose();

    return output;
  }

  @Override
  public DistanceListOutput distanceCovered(Vehicle input) {
    DistanceListOutput output = new DistanceListOutput();
    List<DistanceOutput> dous = new ArrayList<>();
    List<String> vehicleNos = metricRepository.getDistinctVehicles(FlyerUtils.getCurrentDate());
    if(vehicleNos == null || vehicleNos.isEmpty()){
      output.setErrorMessage("No vehicles found");
      return output;
    }
    for(String vehicleNo : vehicleNos){
      DistanceOutput dou = new DistanceOutput();
      Metric fm = metricRepository.getFirstRecordForVehicleNo(vehicleNo, FlyerUtils.getCurrentDate());
      Metric lm = metricRepository.getLastRecordForVehicleNo(vehicleNo, FlyerUtils.getCurrentDate());
      double distance = FlyerUtils.distance(fm.getVehicleLat(), lm.getVehicleLat(), fm.getVehicleLon(), lm.getVehicleLon());
      dou.setVehicleNo(vehicleNo);
      dou.setDistanceCovered((float) distance);
      dous.add(dou);
    }
    output.setDistanceOutputs(dous);
    output.setSuccess(true);
    return output;
  }

  @Override
  public VehicleListOutput overspeed(Vehicle input) {
    VehicleListOutput output = new VehicleListOutput();
    List<Vehicle> vehicles = new ArrayList<>();
    List<String> vehicleNos = overspeedRepository.getDistinctVehicles(FlyerUtils.getCurrentDate());
    if(vehicleNos == null || vehicleNos.isEmpty()){
      output.setErrorMessage("No vehicles found");
      return output;
    }
    for(String vehicleNo : vehicleNos){
      Vehicle v = vehicleRepository.findByVehicleNo(vehicleNo);
      if(v == null){
        log.debug("No vehicle found for vehicleNo: ", vehicleNo);
        continue;
      }
      vehicles.add(v);
    }
    output.setVehicles(vehicles);
    output.setSuccess(true);
    return output;
  }
}

