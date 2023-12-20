package com.openclassrooms.safetynetalerts.IT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.dto.FirestationDTO;
import com.openclassrooms.safetynetalerts.entity.Firestation;
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
public class FirestationControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addFirestation() throws Exception {
        final var url = "/firestation";

        final var newFirestation = FirestationDTO.builder()
                .address("123 Main St")
                .station("5")
                .build();

        MvcResult mvcResult = mockMvc.perform(post(url).content(asJson(newFirestation)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Firestation>>() {
        });

        assertEquals("123 Main St", result.get(13).getAddress());
        assertEquals("5", result.get(13).getStation());
        assertEquals(14, result.size());
    }

    private String asJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    @Test
    void deleteFirestation() throws Exception {
        final var url = "/firestation";

        mockMvc.perform(delete(url)
                        .param("address", "1509 Culver St")
                        .param("station", "3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateFirestation() throws Exception {
        final var url = "/firestation";

        final var dto = FirestationDTO.builder()
                .address("951 LoneTree Rd")
                .station("6")
                .build();
        MvcResult mvcResult = mockMvc.perform(put(url).content(asJson(dto)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Firestation>>() {
        });

        assertEquals("951 LoneTree Rd", result.get(12).getAddress());
        assertEquals("6", result.get(12).getStation());
    }
}
