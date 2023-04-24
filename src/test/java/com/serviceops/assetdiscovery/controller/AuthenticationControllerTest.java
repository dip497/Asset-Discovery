package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.AuthenticationResponse;
import com.serviceops.assetdiscovery.rest.UsersRest;
import com.serviceops.assetdiscovery.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserServiceImpl userService;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRegister() throws Exception {

        UsersRest usersRest = new UsersRest();
        usersRest.setEmail("abc@gmail.com");

        AuthenticationResponse authenticationResponse =
                new AuthenticationResponse("token", "abc@gmail.com", "abc");

        when(userService.register(usersRest)).thenReturn(authenticationResponse);
        mockMvc.perform(
                        post("/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(usersRest)))
                .andExpect(status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("abc@gmail.com"));

    }

    @Test
    public void testAuthenticate() throws Exception {

        UsersRest usersRest = new UsersRest();
        usersRest.setEmail("abc@gmail.com");

        AuthenticationResponse authenticationResponse =
                new AuthenticationResponse("token", "abc@gmail.com", "abc");


        when(userService.authenticate(usersRest)).thenReturn(authenticationResponse);
        mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usersRest))).andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("abc@gmail.com"));



    }


}
