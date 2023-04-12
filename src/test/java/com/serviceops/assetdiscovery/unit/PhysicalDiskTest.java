package com.serviceops.assetdiscovery.unit;

import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class PhysicalDiskTest {
    @Autowired
    CustomRepository customRepository;

    @Test
    void setUp(){
        PhysicalDisk physicalDisk = new PhysicalDisk();

        physicalDisk.setDescription("description...");
        physicalDisk.setSize("133");
        physicalDisk.setName("name...");


        customRepository.save(physicalDisk);
        assert(physicalDisk.getDescription().equals("description..."));
    }
}
