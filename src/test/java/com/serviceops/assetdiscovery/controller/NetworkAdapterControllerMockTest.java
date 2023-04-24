package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;
import com.serviceops.assetdiscovery.service.impl.JwtService;
import com.serviceops.assetdiscovery.service.interfaces.NetworkAdapterService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NetworkAdapterController.class)
@AutoConfigureMockMvc(addFilters = false)
class NetworkAdapterControllerMockTest {

    @MockBean
    private NetworkAdapterService service;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomRepository customRepository;

    @Test
    void getNetworkAdapterList() throws Exception {
        when(service.findAllByRefId(any())).thenReturn(new ArrayList<NetworkAdapterRest>());
        this.mockMvc.perform(get("/{refId}/networkAdapter", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateNetworkAdapter() throws Exception {
        NetworkAdapterRest networkAdapterRest = new NetworkAdapterRest();
        networkAdapterRest.setId(1L);
        networkAdapterRest.setRefId(1L);
        networkAdapterRest.setDescription("this is the description");
        when(service.updateById( networkAdapterRest,1L,1L)).thenReturn(networkAdapterRest);
        mockMvc.perform(put("/{refId}/networkAdapter/{id}", 1L,1L).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(networkAdapterRest)))
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
    void deleteNetworkAdapter() throws Exception {
        when(service.deleteById(anyLong(), anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/{refId}/networkAdapter/{id}", anyLong(), anyLong()))
                .andExpect(status().is2xxSuccessful());
    }
}