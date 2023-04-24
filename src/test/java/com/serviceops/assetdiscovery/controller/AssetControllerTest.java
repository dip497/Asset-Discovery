package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.AllAssetRest;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.service.impl.AssetServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssetController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private AssetServiceImpl assetService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFindPaginatedAssetData() throws Exception {
        AllAssetRest expectedAssetData = new AllAssetRest();
        when(assetService.findPaginatedAssetData(anyInt(), anyInt(), any(), any())).thenReturn(
                expectedAssetData);
        this.mockMvc.perform(get("/asset")).andExpect(status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        AssetRest assetRest = new AssetRest();
        assetRest.setId(1L);
        when(assetService.findById(1L)).thenReturn(assetRest);
        System.out.println(MockMvcResultMatchers.jsonPath("$[1].id"));
        this.mockMvc.perform(get("/asset/{id}", 1L)).andExpect(status().isOk());
    }

    @Test
    void testUpdateById() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("ipAddress", "10.1.1.1");

        AssetRest assetRest = new AssetRest();
        assetRest.setId(1L);
        assetRest.setIpAddress("10.1.1.1");

        when(assetService.updateById(1L, map)).thenReturn(assetRest);
        mockMvc.perform(
                        patch("/asset/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(map)))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ipAddress").value("10.1.1.1"));
    }

    @Test
    public void testDeleteByRefId() throws Exception {
        when(assetService.deleteById(anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/asset/{id}", anyLong())).andExpect(status().isOk());
    }



}
