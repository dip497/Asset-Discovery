package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.service.impl.CredentialsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CredentialsController.class)
@AutoConfigureMockMvc(addFilters = false)
class CredentialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private CredentialsServiceImpl credentialsService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetCredential() throws Exception {
        CredentialsRest credentialsRest = new CredentialsRest();
        credentialsRest.setUsername("abc");

        when(credentialsService.findById(anyLong())).thenReturn(credentialsRest);
        this.mockMvc.perform(get("/credentials/{id}", 1L)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(credentialsRest.getUsername()));

        verify(credentialsService,times(1)).findById(1L);
    }

    @Test
    void testGetAllCredential() throws Exception {
        CredentialsRest credentialsRest = new CredentialsRest();
        credentialsRest.setUsername("abc");

        List<CredentialsRest> credentialsRests = new ArrayList<>();
        credentialsRests.add(credentialsRest);

        when(credentialsService.findAll()).thenReturn(credentialsRests);
        this.mockMvc.perform(get("/credentials")).andExpect(status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].username").value(credentialsRest.getUsername()));

        verify(credentialsService,times(1)).findAll();
    }

    @Test
    void testConnection() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("ipAddress", "192.168.0.109");
        map.put("username", "vishwas");
        map.put("password", "Mind@123");

        when(credentialsService.testConnection(anyMap())).thenReturn(true);

        mockMvc.perform(post("/credentials/testConnection").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(map))).andExpect(status().isOk());

        verify(credentialsService,times(1)).testConnection(map);


    }

    @Test
    void testUpdateCredential() throws Exception {

        CredentialsRest credentialsRest = new CredentialsRest();
        credentialsRest.setId(1L);
        credentialsRest.setUsername("abc");

        when(credentialsService.update(anyLong(), any(CredentialsRest.class))).thenReturn(credentialsRest);

        mockMvc.perform(put("/credentials/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(credentialsRest))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(credentialsRest.getUsername()));

        verify(credentialsService,times(1)).update(1L,credentialsRest);
    }

    @Test
    void testAddCredentials() throws Exception {

        CredentialsRest credentialsRest = new CredentialsRest();
        credentialsRest.setUsername("abc");


        when(credentialsService.save(any(CredentialsRest.class))).thenReturn(credentialsRest);
        mockMvc.perform(post("/credentials").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(credentialsRest))).andExpect(status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(credentialsRest.getUsername()));

        verify(credentialsService,times(1)).save(credentialsRest);
    }

    @Test
    void testDeleteCredentialsById() throws Exception {
        when(credentialsService.deleteById(anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/credentials/1")).andExpect(status().isOk());

        verify(credentialsService,times(1)).deleteById(1L);
    }



}
