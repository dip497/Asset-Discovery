package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.rest.ProcessorRest;
import com.serviceops.assetdiscovery.service.impl.JwtService;
import com.serviceops.assetdiscovery.service.interfaces.NetworkAdapterService;
import com.serviceops.assetdiscovery.service.interfaces.PointingDeviceService;
import com.serviceops.assetdiscovery.service.interfaces.ProcessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static com.serviceops.assetdiscovery.controller.KeyBoardControllerMockTest.asJsonString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProcessorController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProcessorControllerMockTest {
    @MockBean
    private ProcessorService service;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomRepository customRepository;
    @Test
    void findProcessorById() throws Exception {
        when(service.findByRefId(anyLong())).thenReturn(new ArrayList<ProcessorRest>());
        this.mockMvc.perform(get("/{refId}/processor","1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateProcessor() throws Exception {
        ProcessorRest processorRest = new ProcessorRest();
        processorRest.setId(1L);
        processorRest.setRefId(1L);
        processorRest.setDescription("test description");
        when(service.updateByRefId(1L, processorRest)).thenReturn(processorRest);
        mockMvc.perform(
                        put("/{refId}/processor", 1L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(processorRest)))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("test description"));

    }

    @Test
    void deleteProcessorById() throws Exception {
        when(service.deleteById(anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/{refId}/processor",anyLong()))
                .andExpect(status().is2xxSuccessful());
    }
}