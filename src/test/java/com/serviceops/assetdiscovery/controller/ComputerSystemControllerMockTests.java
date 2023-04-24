package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerSystemService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ComputerSystemController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ComputerSystemControllerMockTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private ComputerSystemService computerSystemService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findByRefId() throws Exception {
        ComputerSystemRest computerSystemRest = new ComputerSystemRest();
        computerSystemRest.setRefId(1L);
        List<ComputerSystemRest> computerSystemRests = new ArrayList<>();
        computerSystemRests.add(computerSystemRest);
        when(computerSystemService.findByRefId(anyLong())).thenReturn(computerSystemRests);
        this.mockMvc.perform(get("/{refId}/computersystem", 1L)).andExpect(status().isOk());

        verify(computerSystemService).findByRefId(anyLong());
    }

    @Test
    void updateComputerSystem() throws Exception {
        ComputerSystemRest computerSystemRest = new ComputerSystemRest();
        computerSystemRest.setRefId(1L);
        computerSystemRest.setId(1L);
        computerSystemRest.setManufacturer("Lenovo");
        when(computerSystemService.updateByRefId(1L, computerSystemRest)).thenReturn(computerSystemRest);
        mockMvc.perform(put("/{refId}/computersystem", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(computerSystemRest))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer").value("Lenovo"));

        verify(computerSystemService).updateByRefId(1L, computerSystemRest);
    }

    @Test
    void testDeleteByRefId() throws Exception {
        when(computerSystemService.deleteByRefId(anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/{refId}/computersystem", anyLong())).andExpect(status().isOk());

        verify(computerSystemService).deleteByRefId(anyLong());
    }

}
