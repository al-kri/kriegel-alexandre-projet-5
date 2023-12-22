package com.openclassrooms.safetynetalerts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.assembler.FirestationAssembler;
import com.openclassrooms.safetynetalerts.dto.FirestationDTO;
import com.openclassrooms.safetynetalerts.service.FirestationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FirestationController.class)
@AutoConfigureMockMvc
class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationService firestationService;

    @MockBean
    private FirestationAssembler firestationAssembler;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addFirestation() throws Exception {
        final var url = "/firestation";
        final var dto = FirestationDTO.builder()
                .build();
        mockMvc.perform(post(url).content(asJson(dto)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(firestationService).save(any());
    }

    private String asJson(Object dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }

    @Test
    void deleteFirestation() throws Exception {
        final var url = "/firestation";
        mockMvc.perform(delete(url)
                        .param("address", "")
                        .param("station", ""))
                .andDo(print())
                .andExpect(status().isOk());
        verify(firestationService).delete(anyString(), anyString());
    }

    @Test
    void findAll() throws Exception {
        final var url = "/firestations";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
        verify(firestationService).findAll();
    }

    @Test
    void findByAddress() throws Exception {
        final var url = "/firestationInfo/address";
        mockMvc.perform(get(url).param("address", "addresse"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(firestationService).findByAddress("addresse");
    }

    @Test
    void findByStation() throws Exception {
        final var url = "/firestationInfo/station";
        mockMvc.perform(get(url).param("station", "1"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(firestationService).findByStation("1");
    }

    @Test
    void updateFirestation() throws Exception {
        final var url = "/firestation";
        final var dto = FirestationDTO.builder()
                .build();
        mockMvc.perform(put(url).content(asJson(dto)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(firestationService).update(any());
    }
}