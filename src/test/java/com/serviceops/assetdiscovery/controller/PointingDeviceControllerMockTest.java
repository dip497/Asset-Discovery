package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.PointingDeviceRest;
import com.serviceops.assetdiscovery.service.impl.JwtService;
import com.serviceops.assetdiscovery.service.interfaces.PointingDeviceService;
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

@WebMvcTest(PointingDeviceController.class)
@AutoConfigureMockMvc(addFilters = false)
class PointingDeviceControllerMockTest {

    @MockBean
    private PointingDeviceService service;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomRepository customRepository;

    @Test
    void getPointingDeviceList() throws Exception {
        when(service.findAllByRefId(anyLong())).thenReturn(new ArrayList<PointingDeviceRest>());
        this.mockMvc.perform(get("/{refId}/pointingDevices","1"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePointingDevice() throws Exception {
        PointingDeviceRest pointingDeviceRest = new PointingDeviceRest();
        pointingDeviceRest.setId(1L);
        pointingDeviceRest.setRefId(1L);
        pointingDeviceRest.setDescription("this is the description");
        when(service.updateById( 1L,1L,pointingDeviceRest)).thenReturn(pointingDeviceRest);
        mockMvc.perform(put("/{refId}/pointingDevices/{id}", 1L,1L).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pointingDeviceRest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value(1))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.refId")
                        .value(1))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.description")
                        .value("this is the description"));
    }

    @Test
    void deletePointingDevice() throws Exception {
        when(service.deleteById(anyLong(),anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/{refId}/pointingDevices/{id}",anyLong(),anyLong()))
                .andExpect(status().is2xxSuccessful());
    }
}