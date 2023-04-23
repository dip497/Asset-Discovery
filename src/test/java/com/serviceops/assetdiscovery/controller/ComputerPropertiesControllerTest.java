package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.ComputerPropertiesRest;
import com.serviceops.assetdiscovery.service.impl.ComputerPropertiesServiceImpl;
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

@WebMvcTest(ComputerPropertiesController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ComputerPropertiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private ComputerPropertiesServiceImpl computerPropertiesService;

    @Test
    public void testFindByRefId() throws Exception {

        ComputerPropertiesRest computerPropertiesRest = new ComputerPropertiesRest();
        computerPropertiesRest.setRefId(1);

        List<ComputerPropertiesRest> computerPropertiesRests = new ArrayList<>();
        computerPropertiesRests.add(computerPropertiesRest);

        when(computerPropertiesService.findByRefId(1L)).thenReturn(computerPropertiesRests);
        this.mockMvc.perform(get("/{refId}/computerProperties", 1L)).andExpect(status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].refId").value(computerPropertiesRest.getRefId()));
    }

}
