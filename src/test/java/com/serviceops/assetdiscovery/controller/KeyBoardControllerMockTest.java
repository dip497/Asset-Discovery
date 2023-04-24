package com.serviceops.assetdiscovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.rest.KeyboardRest;
import com.serviceops.assetdiscovery.service.interfaces.KeyboardService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KeyBoardController.class)
@AutoConfigureMockMvc(addFilters = false)
class KeyBoardControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private KeyboardService keyboardService;

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getKeyboards() throws Exception {

        long refId = 1;

        KeyboardRest keyboard1 = new KeyboardRest();
        keyboard1.setRefId(refId);
        keyboard1.setName("keyboard1");
        keyboard1.setId(1L);

        KeyboardRest keyboard2 = new KeyboardRest();
        keyboard2.setRefId(refId);
        keyboard2.setName("keyboard1");
        keyboard2.setId(2L);

        List<KeyboardRest> keyboardRests = Arrays.asList(keyboard1, keyboard2);

        when(keyboardService.findAllByRefId(anyLong())).thenReturn(keyboardRests);

        mockMvc.perform(MockMvcRequestBuilders.get("/{refId}/keyboard", refId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(keyboard1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(keyboard1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(keyboard2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(keyboard2.getName()));

        verify(keyboardService, times(1)).findAllByRefId(1L);
    }

    @Test
    void updateRam() throws Exception {

        long refId = 1;

        KeyboardRest keyboardRest = new KeyboardRest();
        keyboardRest.setRefId(refId);
        keyboardRest.setName("keyboard1");
        keyboardRest.setId(2L);

        when(keyboardService.updateById(anyLong(), anyLong(), any(KeyboardRest.class))).thenReturn(keyboardRest);

        mockMvc.perform(put("/1/keyboard/2").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(keyboardRest))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(keyboardRest.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refId").value(keyboardRest.getRefId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(keyboardRest.getName()));

        verify(keyboardService, times(1)).updateById(1, 2, keyboardRest);

    }

    @Test
    void deleteRam() throws Exception {

        long refId = 1;
        long id = 1;

        when(keyboardService.deleteById(anyLong(), anyLong())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/{refId}/keyboard/{id}", refId, id))
                .andExpect(status().isOk());

        verify(keyboardService, times(1)).deleteById(refId, id);

    }
}