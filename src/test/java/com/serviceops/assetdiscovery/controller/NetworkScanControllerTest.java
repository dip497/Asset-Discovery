package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.entity.enums.IpRangeType;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.impl.NetworkScanServiceImpl;
import com.serviceops.assetdiscovery.service.impl.SchedulerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NetworkScanController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NetworkScanControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NetworkScanServiceImpl networkScanService;

    @MockBean
    SchedulerServiceImpl schedulerService;


    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testScan() throws Exception{

    }

    @Test
    public void testSaveNetworkScan() throws Exception {

        NetworkScanRest networkScanRest = new NetworkScanRest();
        networkScanRest.setIpRangeType(IpRangeType.ENTIRE_NETWORK);

        when(networkScanService.save(networkScanRest)).thenReturn(networkScanRest);
        mockMvc.perform(
                        post("/networkScan").contentType(MediaType.APPLICATION_JSON).content(asJsonString(networkScanRest)))
                .andExpect(status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ipRangeType").value("ENTIRE_NETWORK"));

    }

    @Test
    void testUpdateNetworkScan() throws Exception {
        NetworkScanRest networkScanRest = new NetworkScanRest();
        networkScanRest.setId(1L);
        networkScanRest.setIpRangeType(IpRangeType.ENTIRE_NETWORK);
        when(networkScanService.updateById(1L, networkScanRest)).thenReturn(networkScanRest);

        mockMvc.perform(put("/networkScan/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(networkScanRest))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ipRangeType").value("ENTIRE_NETWORK"));
    }

    @Test
    public void testGetAllNetworkScan() throws Exception {
        NetworkScanRest networkScanRest = new NetworkScanRest();
        networkScanRest.setId(1L);

        when(networkScanService.findById(1L)).thenReturn(networkScanRest);
        this.mockMvc.perform(get("/networkScan/{id}", 1L)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(networkScanRest.getId()));

    }


    @Test
    public void testGetNetworkScanById() throws Exception {
        NetworkScanRest networkScanRest = new NetworkScanRest();
        networkScanRest.setId(1L);

        List<NetworkScanRest> networkScanRests = new ArrayList<>();
        networkScanRests.add(networkScanRest);

        when(networkScanService.findAll()).thenReturn(networkScanRests);
        this.mockMvc.perform(get("/networkScan")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(networkScanRest.getId()));

    }


    @Test
    public void testGetSchedulers() throws Exception {
        SchedulerRest schedulerRest = new SchedulerRest();
        schedulerRest.setNetworkScanRestId(1L);

        List<SchedulerRest> schedulerRests = new ArrayList<>();
        schedulerRests.add(schedulerRest);

        when(schedulerService.findAll()).thenReturn(schedulerRests);
        this.mockMvc.perform(get("/schedulers")).andExpect(status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].networkScanRestId")
                        .value(schedulerRest.getNetworkScanRestId()));

    }


    @Test
    public void testDeleteNetworkScanById() throws Exception {
        doNothing().when(networkScanService).deleteById(1L);
        when(schedulerService.deleteByNetworkScanId(1L)).thenReturn(true);
        this.mockMvc.perform(delete("/networkScan/{id}",1L)).andExpect(status().isOk());
    }


}
