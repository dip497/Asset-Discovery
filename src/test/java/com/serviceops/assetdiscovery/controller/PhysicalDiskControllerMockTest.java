package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.service.impl.JwtService;
import com.serviceops.assetdiscovery.service.interfaces.PhysicalDiskService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(PhysicalDiskController.class)
@AutoConfigureMockMvc(addFilters = false)
class PhysicalDiskControllerMockTest {

    @MockBean
    private PhysicalDiskService service;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomRepository customRepository;

    @Test
    void getPhysicalDiskTest() throws Exception {
        when(service.findByRefId(anyLong())).thenReturn(new ArrayList<PhysicalDiskRest>());
        this.mockMvc.perform(get("/{refId}/physicalDisk","1"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePhysicalDiskTest() throws Exception {
        PhysicalDiskRest physicalDiskRest = new PhysicalDiskRest();
        physicalDiskRest.setId(1L);
        physicalDiskRest.setRefId(1L);
        physicalDiskRest.setDescription("test description");
        when(service.updateByRefId(1L, physicalDiskRest)).thenReturn(physicalDiskRest);
        mockMvc.perform(
                        put("/{refId}/physicalDisk", 1L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(physicalDiskRest)))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("test description"));
    }

    @Test
    void deletePhysicalDiskTest() throws Exception {
        when(service.deleteByRefId(anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/{refId}/physicalDisk",anyLong()))
                .andExpect(status().is2xxSuccessful());
    }
}