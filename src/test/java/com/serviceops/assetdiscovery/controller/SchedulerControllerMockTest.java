package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.entity.enums.Month;
import com.serviceops.assetdiscovery.entity.enums.ScanType;
import com.serviceops.assetdiscovery.entity.enums.Week;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.SchedulersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(SchedulerController.class)
@AutoConfigureMockMvc(addFilters = false)
class SchedulerControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private SchedulersService schedulersService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getScheduler() throws Exception {
        long networkScanRestId = 1L;
        SchedulerRest schedulerRest = new SchedulerRest();
        schedulerRest.setNetworkScanRestId(networkScanRestId);
        schedulerRest.setScanType(ScanType.ONCE);
        schedulerRest.setStartTime(0L);
        schedulerRest.setTime(0L);
        schedulerRest.setDate(0L);
        schedulerRest.setWeek(Week.SUNDAY);
        schedulerRest.setMonth(Month.JANUARY);
        schedulerRest.setInterval(0L);
        schedulerRest.setId(1L);

        when(schedulersService.findByNetworkScanId(anyLong())).thenReturn(schedulerRest);

        mockMvc.perform(MockMvcRequestBuilders.get("/networkScan/1/addScheduler"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.networkScanRestId").value(networkScanRestId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scanType")
                        .value(schedulerRest.getScanType().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startTime").value(schedulerRest.getStartTime()));

        verify(schedulersService, times(1)).findByNetworkScanId(networkScanRestId);

    }

    @Test
    void addScheduler() throws Exception {
        long networkScanRestId = 1L;
        SchedulerRest schedulerRest = new SchedulerRest();
        schedulerRest.setNetworkScanRestId(networkScanRestId);
        schedulerRest.setScanType(ScanType.ONCE);
        schedulerRest.setStartTime(0L);
        schedulerRest.setTime(0L);
        schedulerRest.setDate(0L);
        schedulerRest.setWeek(Week.SUNDAY);
        schedulerRest.setMonth(Month.JANUARY);
        schedulerRest.setInterval(0L);
        schedulerRest.setId(1L);

        when(schedulersService.save(anyLong(), any(SchedulerRest.class))).thenReturn(schedulerRest);

        mockMvc.perform(MockMvcRequestBuilders.post("/networkScan/1/addScheduler")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(schedulerRest)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.networkScanRestId").value(networkScanRestId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scanType")
                        .value(schedulerRest.getScanType().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startTime").value(schedulerRest.getStartTime()));

        verify(schedulersService, times(1)).save(networkScanRestId, schedulerRest);


    }

    @Test
    void updateScheduler() throws Exception {
        long networkScanRestId = 1L;
        SchedulerRest schedulerRest = new SchedulerRest();
        schedulerRest.setNetworkScanRestId(networkScanRestId);
        schedulerRest.setScanType(ScanType.ONCE);
        schedulerRest.setStartTime(0L);
        schedulerRest.setTime(0L);
        schedulerRest.setDate(0L);
        schedulerRest.setWeek(Week.SUNDAY);
        schedulerRest.setMonth(Month.JANUARY);
        schedulerRest.setInterval(0L);
        schedulerRest.setId(1L);

        when(schedulersService.update(anyLong(), any(SchedulerRest.class))).thenReturn(schedulerRest);

        mockMvc.perform(MockMvcRequestBuilders.put("/networkScan/1/addScheduler")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(schedulerRest)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.networkScanRestId").value(networkScanRestId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scanType")
                        .value(schedulerRest.getScanType().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startTime").value(schedulerRest.getStartTime()));

        verify(schedulersService, times(1)).update(networkScanRestId, schedulerRest);

    }


}