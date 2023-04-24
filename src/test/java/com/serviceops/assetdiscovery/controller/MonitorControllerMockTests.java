package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.service.interfaces.MonitorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MonitorController.class)

@AutoConfigureMockMvc(addFilters = false)
class MonitorControllerMockTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonitorService monitorService;

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
    void getMonitors() throws Exception {
        MonitorRest monitorRest = new MonitorRest();
        monitorRest.setRefId(1L);
        monitorRest.setId(1L);
        MonitorRest monitorRest1 = new MonitorRest();
        monitorRest1.setRefId(1L);
        monitorRest1.setId(2L);
        List<MonitorRest> monitorRestList = new ArrayList<>();
        monitorRestList.add(monitorRest);
        monitorRestList.add(monitorRest1);

        when(monitorService.findAllByRefId(anyLong())).thenReturn(monitorRestList);

        this.mockMvc.perform(get("/{refId}/monitor", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L));
        verify(monitorService).findAllByRefId(anyLong());
    }

    @Test
    void updateMonitor() throws Exception {
        MonitorRest monitorRest = new MonitorRest();
        monitorRest.setRefId(1L);
        monitorRest.setId(1L);
        monitorRest.setManufacturer("Intel");
        when(monitorService.updateById(1L, 1L, monitorRest)).thenReturn(monitorRest);
        this.mockMvc.perform(put("/{refId}/monitor/{id}", 1L, 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(monitorRest)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));

        verify(monitorService, times(1)).updateById(1L, 1L, monitorRest);

    }

    @Test
    void deleteMonitor() throws Exception {
        when(monitorService.deleteById(anyLong(), anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/{refId}/monitor/{id}", anyLong(), anyLong()))
                .andExpect(status().isOk());
        verify(monitorService).deleteById(anyLong(), anyLong());
    }
}
