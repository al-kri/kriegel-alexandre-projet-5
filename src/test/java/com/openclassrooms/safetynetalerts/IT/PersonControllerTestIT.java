package com.openclassrooms.safetynetalerts.IT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.dto.PersonDTO;
import com.openclassrooms.safetynetalerts.entity.Person;
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
public class PersonControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addPerson() throws Exception {
        final var url = "/person";

        final var dto = PersonDTO.builder()
                .firstName("Paul")
                .lastName("Testperson")
                .build();
        MvcResult mvcResult = mockMvc.perform(post(url).content(asJson(dto)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Person>>() {
        });

        assertEquals(24, result.size());
        assertEquals("Paul", result.get(23).getFirstName());
        assertEquals("Testperson", result.get(23).getLastName());
        assertEquals("John", result.get(0).getFirstName());
    }

    private String asJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    @Test
    void deletePerson() throws Exception {
        final var url = "/person";

        mockMvc.perform(delete(url)
                        .param("firstName", "John")
                        .param("lastName", "Boyd"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updatePerson() throws Exception {
        final var url = "/person";

        final var updatedPerson = PersonDTO.builder()
                .firstName("John")
                .lastName("Boyd")
                .address("New address")
                .build();

        MvcResult mvcResult = mockMvc.perform(put(url).content(asJson(updatedPerson)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Person>>() {
        });

        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Boyd", result.get(0).getLastName());
        assertEquals("New address", result.get(0).getAddress());
    }
}
