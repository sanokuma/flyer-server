package com.intuit.flyer.bean.output;

import lombok.Data;

import java.util.List;

@Data
public class DistanceListOutput extends ErrorOutput{

    private List<DistanceOutput> distanceOutputs;

}
