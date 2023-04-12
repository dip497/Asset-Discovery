package com.serviceops.assetdiscovery.mock;

import com.serviceops.assetdiscovery.controller.PhysicalDiskController;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.PhysicalDiskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(PhysicalDiskController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PhysicalDiskTest {

    @MockBean
    private PhysicalDiskService physicalDiskService;

    @Test
    public void testAddPhysicalDisk() throws Exception {

    }
}
