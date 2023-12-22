package com.openclassrooms.safetynetalerts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.assembler.MedicalRecordAssembler;
import com.openclassrooms.safetynetalerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
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

@WebMvcTest(MedicalRecordController.class)
@AutoConfigureMockMvc
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @MockBean
    private MedicalRecordAssembler medicalRecordAssembler;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addMedicalRecord() throws Exception {
        final var url = "/medicalRecord";
        final var dto = MedicalRecordDTO.builder()
                .build();
        mockMvc.perform(post(url).content(asJson(dto)).contentType("application/json")).andDo(print()).andExpect(status().isCreated());

    }

    private String asJson(Object dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }

    @Test
    void deleteMedicalRecord() throws Exception {
        final var url = "/medicalRecord";
        mockMvc.perform(delete(url)
                        .param("firstName", "")
                        .param("lastName", ""))
                .andDo(print())
                .andExpect(status().isOk());
        verify(medicalRecordService).delete(anyString(), anyString());
    }

    @Test
    void findAll() throws Exception {
        final var url = "/medicalRecords";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
        verify(medicalRecordService).findAll();
    }

    @Test
    void updateMedicalRecord() throws Exception {
        final var url = "/medicalRecord";
        final var dto = MedicalRecordDTO.builder()
                .build();
        mockMvc.perform(put(url).content(asJson(dto)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(medicalRecordService).update(any());
    }
}