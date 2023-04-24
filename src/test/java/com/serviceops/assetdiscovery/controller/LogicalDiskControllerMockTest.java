package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.LogicalDiskService;
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

@WebMvcTest(LogicalDiskController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LogicalDiskControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogicalDiskService logicalDiskService;

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
    void getLogicalDisks() throws Exception {
        LogicalDiskRest logicalDiskRest = new LogicalDiskRest();
        logicalDiskRest.setRefId(1l);
        logicalDiskRest.setId(1l);
        LogicalDiskRest logicalDiskRest1 = new LogicalDiskRest();
        logicalDiskRest1.setRefId(1l);
        logicalDiskRest1.setId(2l);

        List<LogicalDiskRest> logicalDiskRests = new ArrayList<>();
        logicalDiskRests.add(logicalDiskRest);
        logicalDiskRests.add(logicalDiskRest1);

        when(logicalDiskService.findAllByRefId(anyLong())).thenReturn(logicalDiskRests);
        this.mockMvc.perform(get("/{refId}/logicaldisk",1l).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(logicalDiskRest))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2l));
        verify(logicalDiskService,times(1)).findAllByRefId(anyLong());

    }

    @Test
    void updateLogicalDisk() throws Exception {
        LogicalDiskRest logicalDiskRest = new LogicalDiskRest();

        logicalDiskRest.setRefId(1l);
        logicalDiskRest.setName("sda1");
        logicalDiskRest.setId(1l);

        when(logicalDiskService.updateById(1l,1l,logicalDiskRest)).thenReturn(logicalDiskRest);

        this.mockMvc.perform(put("/{refId}/logicaldisk/{id}",1l,1l)
                .content(asJsonString(logicalDiskRest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("sda1"));
        verify(logicalDiskService,times(1)).updateById(1l,1l,logicalDiskRest);
    }
    @Test
    void deleteLogicalDisk() throws Exception {
        when(logicalDiskService.deleteById(anyLong(),anyLong())).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/{refId}/logicaldisk/{id}",anyLong(),anyLong()))
                .andExpect(status().isOk());
        verify(logicalDiskService,times(1)).deleteById(anyLong(),anyLong());
    }

}
