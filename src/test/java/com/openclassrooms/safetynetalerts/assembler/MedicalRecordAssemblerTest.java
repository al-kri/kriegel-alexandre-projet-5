package com.openclassrooms.safetynetalerts.assembler;

import com.openclassrooms.safetynetalerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MedicalRecordAssemblerTest {

    private MedicalRecordAssembler medicalRecordAssembler;

    @BeforeEach
    void beforeEach() {

        medicalRecordAssembler = new MedicalRecordAssembler();

    }

    @Test
    void toEntity() {

        MedicalRecordDTO medicalRecordDTO = MedicalRecordDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .birthdate("01/15/1974")
                .medications(List.of("None"))
                .allergies(List.of("Fur"))
                .build();

        MedicalRecord result = medicalRecordAssembler.toEntity(medicalRecordDTO);

        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("01/15/1974", result.getBirthdate());
        assertEquals(List.of("None"), result.getMedications());
        assertEquals(List.of("Fur"), result.getAllergies());
    }
}