package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.HardwarePropertiesRest;
import com.serviceops.assetdiscovery.service.impl.HardwarePropertiesServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HardwarePropertiesController.class)
@AutoConfigureMockMvc(addFilters = false)
public class HardwarePropertiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private HardwarePropertiesServiceImpl hardwarePropertiesService;


    @Test
    public void testFindByRefId() throws Exception {
        HardwarePropertiesRest hardwarePropertiesRest = new HardwarePropertiesRest();
        hardwarePropertiesRest.setRefId(1L);
        List<HardwarePropertiesRest> hardwarePropertiesRests = new ArrayList<>();
        hardwarePropertiesRests.add(hardwarePropertiesRest);

        when(hardwarePropertiesService.findByRefId(1L)).thenReturn(hardwarePropertiesRests);
        this.mockMvc.perform(get("/{refId}/hardwareProperties", 1L)).andExpect(status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].refId").value(hardwarePropertiesRest.getRefId()));

    }

}
