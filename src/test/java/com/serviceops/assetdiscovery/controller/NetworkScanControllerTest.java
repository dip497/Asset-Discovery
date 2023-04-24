package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.entity.enums.IpRangeType;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.NetworkScanService;
import com.serviceops.assetdiscovery.service.interfaces.SchedulersService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NetworkScanController.class)
@AutoConfigureMockMvc(addFilters = false)
class NetworkScanControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NetworkScanService networkScanService;

    @MockBean
    SchedulersService schedulersService;


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
    void testScan() throws Exception {
        doNothing().when(networkScanService).scan(anyLong());

        mockMvc.perform(get("/networkScan/scan/1")).andExpect(status().isOk());

        verify(networkScanService, times(1)).scan(1L);

    }

    @Test
    void testSaveNetworkScan() throws Exception {

        NetworkScanRest networkScanRest = new NetworkScanRest();
        networkScanRest.setIpRangeType(IpRangeType.ENTIRE_NETWORK);

        when(networkScanService.save(any(NetworkScanRest.class))).thenReturn(networkScanRest);
        mockMvc.perform(post("/networkScan").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(networkScanRest))).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ipRangeType").value("ENTIRE_NETWORK"));

        verify(networkScanService, times(1)).save(networkScanRest);


    }

    @Test
    void testUpdateNetworkScan() throws Exception {
        NetworkScanRest networkScanRest = new NetworkScanRest();
        networkScanRest.setId(1L);
        networkScanRest.setIpRangeType(IpRangeType.ENTIRE_NETWORK);
        when(networkScanService.updateById(anyLong(), any(NetworkScanRest.class))).thenReturn(networkScanRest);

        mockMvc.perform(put("/networkScan/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(networkScanRest))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ipRangeType").value("ENTIRE_NETWORK"));

        verify(networkScanService, times(1)).updateById(1L,networkScanRest);

    }

    @Test
    void testGetAllNetworkScan() throws Exception {
        NetworkScanRest networkScanRest = new NetworkScanRest();
        networkScanRest.setId(1L);

        when(networkScanService.findById(anyLong())).thenReturn(networkScanRest);
        mockMvc.perform(get("/networkScan/{id}", 1L)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(networkScanRest.getId()));

        verify(networkScanService, times(1)).findById(1L);


    }


    @Test
    void testGetNetworkScanById() throws Exception {
        NetworkScanRest networkScanRest = new NetworkScanRest();
        networkScanRest.setId(1L);

        List<NetworkScanRest> networkScanRests = new ArrayList<>();
        networkScanRests.add(networkScanRest);

        when(networkScanService.findAll()).thenReturn(networkScanRests);
        mockMvc.perform(get("/networkScan")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(networkScanRest.getId()));

        verify(networkScanService, times(1)).findAll();


    }


    @Test
    void testGetSchedulers() throws Exception {
        SchedulerRest schedulerRest = new SchedulerRest();
        schedulerRest.setNetworkScanRestId(1L);

        List<SchedulerRest> schedulerRests = new ArrayList<>();
        schedulerRests.add(schedulerRest);

        when(schedulersService.findAll()).thenReturn(schedulerRests);
        mockMvc.perform(get("/schedulers")).andExpect(status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].networkScanRestId")
                        .value(schedulerRest.getNetworkScanRestId()));

        verify(schedulersService, times(1)).findAll();


    }


    @Test
    void testDeleteNetworkScanById() throws Exception {
        when(networkScanService.deleteById(1L)).thenReturn(true);
        when(schedulersService.deleteByNetworkScanId(1L)).thenReturn(true);
        mockMvc.perform(delete("/networkScan/{id}", 1L)).andExpect(status().isOk());

        verify(schedulersService,times(1)).deleteByNetworkScanId(1L);
        verify(networkScanService,times(1)).deleteById(1L);

    }


}
