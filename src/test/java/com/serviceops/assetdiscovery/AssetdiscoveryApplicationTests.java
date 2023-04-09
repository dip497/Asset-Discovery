package com.serviceops.assetdiscovery;
import java.sql.Timestamp;

import com.serviceops.assetdiscovery.entity.PointingDevice;
import com.serviceops.assetdiscovery.rest.PointingDeviceRest;
import com.serviceops.assetdiscovery.utils.mapper.PointingDeviceOps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssetdiscoveryApplicationTests {

    @Test
    void entityToRest() {

        PointingDevice pointingDevice = new PointingDevice();
        pointingDevice.setNumberOfButtons(0);
        pointingDevice.setDescription("description");
        pointingDevice.setPointingType("pointing type");
        pointingDevice.setPnpDeviceId("9");
        pointingDevice.setManufacturer("manufacturer");
        pointingDevice.setDeviceStatus("ok");
        pointingDevice.setRefId(0L);
        pointingDevice.setSerialNumber("00000");
        pointingDevice.setCreatedBy("dipendra");
        pointingDevice.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
        pointingDevice.setUpdatedBy("dipendra");
        pointingDevice.setUpdatedTime(new Timestamp(new java.util.Date().getTime()));
        pointingDevice.setId(0L);



        PointingDeviceRest pointingDeviceRest = new PointingDeviceRest();

        PointingDeviceOps pointingDeviceOps = new PointingDeviceOps(pointingDevice,pointingDeviceRest);
        System.out.println("converting entity to rest");
        pointingDeviceRest = pointingDeviceOps.entityToRest();
        System.out.println("entity");
        System.out.println(pointingDevice);
        System.out.println("base");
        System.out.println(pointingDeviceRest);




    }

    @Test
    void restToEntity(){
        PointingDeviceRest pointingDeviceRest = new PointingDeviceRest();
        pointingDeviceRest.setNumberOfButtons(0);
        pointingDeviceRest.setDescription("description");
        pointingDeviceRest.setPointingType("ll");
        pointingDeviceRest.setPnpDeviceId("kk");
        pointingDeviceRest.setManufacturer("lk");
        pointingDeviceRest.setDeviceStatus("kg");
        pointingDeviceRest.setRefId(0L);
        pointingDeviceRest.setSerialNumber("hjg");
        pointingDeviceRest.setCreatedBy("gjf");
        pointingDeviceRest.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
        pointingDeviceRest.setUpdatedBy("kjgfkj");
        pointingDeviceRest.setUpdatedTime(new Timestamp(new java.util.Date().getTime()));
        pointingDeviceRest.setId(0L);

        PointingDevice pointingDevice = new PointingDevice();

        System.out.println("converting rest to entity");
        PointingDeviceOps pointingDeviceOps = new PointingDeviceOps(pointingDevice,pointingDeviceRest);
        pointingDevice = pointingDeviceOps.restToEntity();
        System.out.println("entity");
        System.out.println(pointingDevice);
        System.out.println("base");
        System.out.println(pointingDeviceRest);

    }
    @Test
    void fetch(){
    }


}
