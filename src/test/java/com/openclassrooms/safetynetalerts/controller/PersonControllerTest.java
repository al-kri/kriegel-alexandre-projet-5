package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.assembler.PersonAssembler;
import com.openclassrooms.safetynetalerts.dto.PersonDTO;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
import com.openclassrooms.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.openclassrooms.safetynetalerts.utils.Utils.asJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonAssembler personAssembler;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    void addPerson() throws Exception {
        final var url = "/person";
        final var dto = PersonDTO.builder()
                .build();
        mockMvc.perform(post(url).content(asJson(dto)).contentType("application/json")).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void deletePerson() throws Exception {
        final var url = "/person";
        mockMvc.perform(delete(url)
                        .param("firstName", "")
                        .param("lastName", ""))
                .andDo(print())
                .andExpect(status().isOk());
        verify(personService).delete(anyString(), anyString());
    }

    @Test
    void findAll() throws Exception {
        final var url = "/persons";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
        verify(personService).findAll();
    }

    @Test
    void updatePerson() throws Exception {
        final var url = "/person";
        final var dto = PersonDTO.builder()
                .build();
        mockMvc.perform(put(url).content(asJson(dto)).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(personService).update(any());
    }
}