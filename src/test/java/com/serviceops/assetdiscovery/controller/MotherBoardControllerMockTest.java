package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MotherBoardController.class)
@AutoConfigureMockMvc(addFilters = false)
class MotherBoardControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private MotherBoardService motherBoardService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getKeyboards() throws Exception {

        long refId = 1;

        MotherBoardRest motherBoardRest = new MotherBoardRest();
        motherBoardRest.setRefId(refId);
        motherBoardRest.setManufacturer("manufacturer");
        motherBoardRest.setSerialNumber("serialNumber");
        motherBoardRest.setVersion("version");
        motherBoardRest.setId(1L);

        when(motherBoardService.findByRefId(anyLong())).thenReturn(List.of(motherBoardRest));

        mockMvc.perform(MockMvcRequestBuilders.get("/{refId}/motherBoard", refId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(motherBoardRest.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].refId").value(motherBoardRest.getRefId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].manufacturer")
                        .value(motherBoardRest.getManufacturer())).andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].serialNumber").value(motherBoardRest.getSerialNumber()))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].version").value(motherBoardRest.getVersion()));

        verify(motherBoardService, times(1)).findByRefId(1L);
    }

    @Test
    void updateMotherBoard() throws Exception {

        long refId = 1;

        MotherBoardRest motherBoardRest = new MotherBoardRest();
        motherBoardRest.setRefId(refId);
        motherBoardRest.setManufacturer("manufacturer");
        motherBoardRest.setSerialNumber("serialNumber");
        motherBoardRest.setVersion("version");
        motherBoardRest.setId(1L);

        when(motherBoardService.updateByRefId(refId, motherBoardRest)).thenReturn(motherBoardRest);

        mockMvc.perform(put("/1/motherBoard").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(motherBoardRest))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(motherBoardRest.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(motherBoardRest.getRefId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value(motherBoardRest.getManufacturer())).andExpect(
                        MockMvcResultMatchers.jsonPath("$.serialNumber").value(motherBoardRest.getSerialNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.version").value(motherBoardRest.getVersion()));

        verify(motherBoardService, times(1)).updateByRefId(1, motherBoardRest);

    }

    @Test
    void deleteMotherBoard() throws Exception {

        long refId = 1;
        when(motherBoardService.deleteByRefId(anyLong())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/{refId}/motherBoard", refId))
                .andExpect(status().isOk());

        verify(motherBoardService, times(1)).deleteByRefId(refId);

    }

}