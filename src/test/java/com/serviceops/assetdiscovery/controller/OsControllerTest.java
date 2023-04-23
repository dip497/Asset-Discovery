package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.entity.enums.Architecture;
import com.serviceops.assetdiscovery.rest.OSRest;
import com.serviceops.assetdiscovery.service.impl.OsServiceImpl;
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

@WebMvcTest(OSController.class)
@AutoConfigureMockMvc(addFilters = false)
public class OsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private OsServiceImpl osService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFindByRefId() throws Exception {
        OSRest osRest = new OSRest();
        osRest.setRefId(1L);
        List<OSRest> osRests = new ArrayList<>();
        osRests.add(osRest);
        when(osService.findByRefId(1L)).thenReturn(osRests);
        this.mockMvc.perform(get("/{refId}/os", 1L)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].refId").value(osRest.getRefId()));
    }

    @Test
    void updateRam() throws Exception {
        OSRest osRest = new OSRest();
        osRest.setId(1L);
        osRest.setRefId(1L);
        osRest.setArchitecture(Architecture.SIXTY_FOUR);
        when(osService.updateByRefId(1L, osRest)).thenReturn(osRest);
        mockMvc.perform(
                        put("/{refId}/os", 1L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(osRest)))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.architecture").value("SIXTY_FOUR"));
    }

    @Test
    public void testDeleteByRefId() throws Exception {
        when(osService.deleteByRefId(anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/{refId}/os", anyLong())).andExpect(status().isOk());
    }
}
