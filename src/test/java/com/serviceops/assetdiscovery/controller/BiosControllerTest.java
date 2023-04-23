package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.service.impl.BiosServiceImpl;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BiosController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BiosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private BiosServiceImpl biosService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFindByRefId() throws Exception {
        BiosRest biosRest = new BiosRest();
        biosRest.setRefId(1L);
        List<BiosRest> biosRests = new ArrayList<>();
        biosRests.add(biosRest);
        when(biosService.findByRefId(1L)).thenReturn(biosRests);
        this.mockMvc.perform(get("/{refId}/bios", 1L)).andExpect(status().isOk());
    }

    @Test
    void updateRam() throws Exception {
        BiosRest biosRest = new BiosRest();
        biosRest.setId(1L);
        biosRest.setRefId(1L);
        biosRest.setManufacturer("LENOVO");
        when(biosService.updateByRefId(1L, biosRest)).thenReturn(biosRest);
        mockMvc.perform(put("/{refId}/bios", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(biosRest))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer").value("LENOVO"));
    }

    @Test
    public void testDeleteByRefId() throws Exception {
        when(biosService.deleteByRefId(anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/{refId}/bios", anyLong())).andExpect(status().isOk());
    }

}
