package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.assembler.SafetyAlertAssembler;
import com.openclassrooms.safetynetalerts.service.FirestationService;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
import com.openclassrooms.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SafetyAlertController.class)
@AutoConfigureMockMvc
class SafetyAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private FirestationService firestationService;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @MockBean
    private SafetyAlertAssembler safetyAlertAssembler;

    @Test
    void childAlert() throws Exception {
        final var url = "/childAlert";
        mockMvc.perform(get(url).param("address", "adresse au pif")).andDo(print()).andExpect(status().isOk());
        verify(personService).findAll();
        verify(medicalRecordService).findByPersonList(anyList());
        verify(safetyAlertAssembler).toChildAlertInfoModel(anyString(), anyList(), anyList());
    }

    @Test
    void fireAlert() throws Exception {
        final var url = "/fire";
        mockMvc.perform(get(url).param("address", anyString())).andDo(print()).andExpect(status().isOk());
        verify(firestationService).findByAddress(anyString());
        verify(personService).findAllByFirestation(anyList());
        verify(medicalRecordService).findByPersonList(anyList());
        verify(safetyAlertAssembler).toFireAlertInfoModel(anyList(), anyList(), anyList());
    }

    @Test
    void firestationAlert() throws Exception {
        final var url = "/firestation";
        mockMvc.perform(get(url).param("station_number", anyString())).andDo(print()).andExpect(status().isOk());
        verify(firestationService).findByStation(anyString());
        verify(personService).findAllByFirestation(anyList());
        verify(medicalRecordService).findByPersonList(anyList());
        verify(safetyAlertAssembler).toFirestationAlertInfoModel(anyList(), anyList(), anyList());
    }

    @Test
    void floodAlert() throws Exception {
        final var url = "/flood/stations";
        mockMvc.perform(get(url).param("stations", "1")
                        .param("stations", "2"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(firestationService, times(2)).findByStation(anyString());
        verify(personService).findAllByFirestation(anyList());
        verify(medicalRecordService).findByPersonList(anyList());
        verify(safetyAlertAssembler).toFloodAlertInfoModel(anyList(), anyList(), anyList());
    }

    @Test
    void phoneAlert() throws Exception {
        final var url = "/phoneAlert";
        mockMvc.perform(get(url).param("firestation_number", anyString())).andDo(print()).andExpect(status().isOk());
        verify(firestationService).findByStation(anyString());
        verify(personService).findAllByFirestation(anyList());
    }


}