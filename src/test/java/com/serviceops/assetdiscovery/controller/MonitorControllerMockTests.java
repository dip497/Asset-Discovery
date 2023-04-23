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
class MontiorControllerMockTests {

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
    public void getMonitors() throws Exception {
        MonitorRest monitorRest = new MonitorRest();
        monitorRest.setRefId(1l);
        monitorRest.setId(1l);
        MonitorRest monitorRest1 = new MonitorRest();
        monitorRest1.setRefId(1l);
        monitorRest1.setId(2l);
        List<MonitorRest> monitorRestList = new ArrayList<>();
        monitorRestList.add(monitorRest);
        monitorRestList.add(monitorRest1);

        when(monitorService.findAllByRefId(anyLong())).thenReturn(monitorRestList);

        this.mockMvc.perform(get("/{refId}/monitor",1l)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2l));
    }

    @Test
    public void updateMonitor() throws Exception {
        MonitorRest monitorRest = new MonitorRest();
        monitorRest.setRefId(1l);
        monitorRest.setId(1l);
        monitorRest.setManufacturer("Intel");
        when(monitorService.updateById(1l,1l,monitorRest)).thenReturn(monitorRest);
        this.mockMvc.perform(put("/{refId}/monitor/{id}",1l,1l).
                contentType(MediaType.APPLICATION_JSON).content(asJsonString(monitorRest)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));

        verify(monitorService,times(1)).updateById(1l,1l,monitorRest);

    }
    @Test
    public void deleteMonitor() throws Exception {
        when(monitorService.deleteById(anyLong(),anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/{refId}/monitor/{id}",anyLong(),anyLong()))
                .andExpect(status().isOk());
    }
}
