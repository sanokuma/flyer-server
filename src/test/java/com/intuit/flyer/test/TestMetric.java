package com.intuit.flyer.test;

import com.intuit.flyer.common.FlyerUtils;
import com.intuit.flyer.entity.Metric;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ComponentScan(basePackages = "com.intuit.flyer")
public class TestMetric extends FlyerTests{

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Transactional
    @Test
    public void testMetric() throws Exception {
        Metric m = new Metric();
        m.setVehicleNo("KA 04 AB 2222");
        m.setVehicleFuel(80f);
        m.setVehicleLat(100f);
        m.setVehicleLon(20f);
        m.setVehicleSpeed(60f);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/metric/insert").contentType(MediaType.APPLICATION_JSON)
                .content(FlyerUtils.asJsonString(m))).andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType("application/json"));
    }
}
