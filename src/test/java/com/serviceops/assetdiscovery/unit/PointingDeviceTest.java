package com.serviceops.assetdiscovery.unit;

import com.serviceops.assetdiscovery.entity.PointingDevice;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PointingDeviceTest {

    @Autowired
    CustomRepository customRepository;

 /*   @Test
    void setUp(){
        PointingDevice pointingDevice = new PointingDevice();
        pointingDevice.setDescription("description....");
        pointingDevice.setPointingType("pointing device type");
        pointingDevice.setPnpDeviceId("point device id");
        pointingDevice.setDeviceStatus("point device status");

        customRepository.save(pointingDevice);
        assert(pointingDevice.getDescription().equals("description..."));
//        customRepository.findByColumn("description","description...",PointingDevice.class);
    }*/
}
