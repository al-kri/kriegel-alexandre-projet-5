package com.openclassrooms.safetynetalerts.IT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MedicalRecordControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addMedicalRecord() throws Exception {
        final var url = "/medicalRecord";

        final var newMedicalRecord = MedicalRecordDTO.builder()
                .firstName("New")
                .lastName("MedicalRecord")
                .medications(List.of("Temesta 100mg"))
                .allergies(List.of("Peanut"))
                .build();
        MvcResult mvcResult = mockMvc.perform(post(url).content(asJson(newMedicalRecord)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<MedicalRecord>>() {
        });

        assertEquals(24, result.size());
        assertEquals("New", result.get(23).getFirstName());
        assertEquals("MedicalRecord", result.get(23).getLastName());
        assertEquals(List.of("Temesta 100mg"), result.get(23).getMedications());
        assertEquals(List.of("Peanut"), result.get(23).getAllergies());
    }

    private String asJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    @Test
    void deleteMedicalRecord() throws Exception {
        final var url = "/medicalRecord";

        mockMvc.perform(delete(url)
                        .param("firstName", "John")
                        .param("lastName", "Boyd"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateMedicalRecord() throws Exception {
        final var url = "/medicalRecord";

        final var updatedMedicalRecord = MedicalRecordDTO.builder()
                .firstName("John")
                .lastName("Boyd")
                .medications(List.of("aznol:350mg"))
                .allergies(List.of("None"))
                .build();
        MvcResult mvcResult = mockMvc.perform(put(url).content(asJson(updatedMedicalRecord)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<MedicalRecord>>() {
        });

        assertEquals(23, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Boyd", result.get(0).getLastName());
        assertEquals(List.of("aznol:350mg"), result.get(0).getMedications());
        assertEquals(List.of("None"), result.get(0).getAllergies());
    }
}
