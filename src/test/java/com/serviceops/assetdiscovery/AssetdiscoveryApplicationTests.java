package com.serviceops.assetdiscovery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceops.assetdiscovery.config.JwtAuthenticationFilter;
import com.serviceops.assetdiscovery.controller.OSController;
import com.serviceops.assetdiscovery.rest.OSRest;
import com.serviceops.assetdiscovery.service.impl.OsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OSController.class)
@AutoConfigureMockMvc(addFilters = false)
class OsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private OsServiceImpl osService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testFindById() throws Exception {
        when(osService.findByRefId(anyLong())).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/{refId}/os", anyLong())).andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        when(osService.deleteByRefId(anyLong())).thenReturn(true);
        this.mockMvc.perform(delete("/{refId}/os", anyLong())).andExpect(status().isOk());
    }

//    @Test
//    public void testUpdateById() throws Exception {
//
//        OSRest osRest = new OSRest();
//
//        when(osService.updateByRefId(anyLong(), osRest)).thenReturn(osRest);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{refId}/os", "1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(osRest));
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk());
//
//    }



}
