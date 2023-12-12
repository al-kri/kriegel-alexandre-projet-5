package com.openclassrooms.safetynetalerts.assembler;

import com.openclassrooms.safetynetalerts.dto.FirestationDTO;
import com.openclassrooms.safetynetalerts.entity.Firestation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FirestationAssemblerTest {

    private FirestationAssembler firestationAssembler;

    @BeforeEach
    void beforeEach() {
        firestationAssembler = new FirestationAssembler();
    }

    @Test
    void toEntity() {

        FirestationDTO firestation1 = FirestationDTO.builder()
                .address("address")
                .station("1")
                .build();

        Firestation result = firestationAssembler.toEntity(firestation1);

        assertEquals("address", result.getAddress());
        assertEquals("1", result.getStation());

    }
}