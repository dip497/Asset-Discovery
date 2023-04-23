package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.RamRest;
import com.serviceops.assetdiscovery.service.interfaces.RamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RamController.class)
@AutoConfigureMockMvc(addFilters = false)
class RamControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private RamService ramService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getRamReturnListOfRamRest() throws Exception {

        long refId = 1;
        RamRest ram1 = new RamRest();
        ram1.setRefId(1L);
        ram1.setSerialNumber("");
        ram1.setManufacturer("");
        ram1.setSize(0L);
        ram1.setMemoryType("");
        ram1.setWidth(0L);
        ram1.setClockSpeed(0L);
        ram1.setBankLocater("");
        ram1.setId(1L);

        RamRest ram2 = new RamRest();
        ram1.setRefId(1L);
        ram1.setSerialNumber("");
        ram1.setManufacturer("");
        ram1.setSize(0L);
        ram1.setMemoryType("");
        ram1.setWidth(0L);
        ram1.setClockSpeed(0L);
        ram1.setBankLocater("");
        ram1.setId(2L);

        List<RamRest> rams = Arrays.asList(ram1, ram2);

        when(ramService.findAllByRefId(anyLong())).thenReturn(rams);

        mockMvc.perform(MockMvcRequestBuilders.get("/{refId}/ram", refId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(ram1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].size").value(ram1.getSize()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(ram2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].size").value(ram2.getSize()));

        verify(ramService, times(1)).findAllByRefId(refId);
    }

    @Test
    void updateRam() throws Exception {

        RamRest ramRest = new RamRest();
        ramRest.setId(456L);
        ramRest.setRefId(123L);
        ramRest.setSize(8L);

        when(ramService.updateById(123L, 456L, ramRest)).thenReturn(ramRest);

        mockMvc.perform(
                        put("/123/ram/456").contentType(MediaType.APPLICATION_JSON).content(asJsonString(ramRest)))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(456))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(123))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(8));

        verify(ramService, times(1)).updateById(123L, 456L, ramRest);
    }

    @Test
    void deleteRam() throws Exception {

        long refId = 1;
        long id = 1;

        when(ramService.deleteById(anyLong(), anyLong())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/{refId}/ram/{id}", refId, id))
                .andExpect(status().isOk());

        verify(ramService, times(1)).deleteById(refId, id);
    }
}