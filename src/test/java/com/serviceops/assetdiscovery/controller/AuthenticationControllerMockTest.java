package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.entity.enums.Role;
import com.serviceops.assetdiscovery.rest.AuthenticationResponse;
import com.serviceops.assetdiscovery.rest.UsersRest;
import com.serviceops.assetdiscovery.service.interfaces.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private UsersService usersService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void register() throws Exception {
        UsersRest usersRest = new UsersRest();
        usersRest.setName("dipendra");
        usersRest.setEmail("dipendra@gmail.com");
        usersRest.setPassword("Sharmaji@497");
        usersRest.setRole(Role.USER);
        usersRest.setId(1L);

        AuthenticationResponse authenticationResponse =
                new AuthenticationResponse("token", "dipendra497@gmail.com", "dipendra");

        when(usersService.register(any(UsersRest.class))).thenReturn(authenticationResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usersRest)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(authenticationResponse.getToken()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(authenticationResponse.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authenticationResponse.getName()));

        verify(usersService, times(1)).register(usersRest);

    }

    @Test
    void authenticate() throws Exception {

        UsersRest usersRest = new UsersRest();
        usersRest.setName("dipendra");
        usersRest.setEmail("dipendra@gmail.com");
        usersRest.setPassword("Sharmaji@497");

        AuthenticationResponse authenticationResponse =
                new AuthenticationResponse("token", "dipendra497@gmail.com", "dipendra");

        when(usersService.authenticate(any(UsersRest.class))).thenReturn(authenticationResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usersRest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(authenticationResponse.getToken()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(authenticationResponse.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authenticationResponse.getName()));

        verify(usersService, times(1)).authenticate(usersRest);
    }
}