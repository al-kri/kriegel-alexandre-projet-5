package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirestationRepositoryImplTest {

    private FirestationRepositoryImpl firestationRepository;

    @BeforeEach
    public void beforeEach() {
        JsonData.loadJsonData();
        firestationRepository = new FirestationRepositoryImpl();
        assertEquals(13, JsonData.firestations.size());
        assertEquals(13, firestationRepository.getFirestationList().size());
    }

    @Test
    void delete() {
        firestationRepository.delete("1509 Culver St", "3");
        assertEquals(12, firestationRepository.getFirestationList().size());
    }

    @Test
    void findAll() {
        firestationRepository.findAll();
        assertEquals(13, firestationRepository.getFirestationList().size());
    }

    @Test
    void findByAddress() {
        firestationRepository.findByAddress("1509 Culver St");
        assertEquals("1509 Culver St", "1509 Culver St");
    }

    @Test
    void findByStation() {
        firestationRepository.findByStation("1");
        assertEquals("1", "1");
    }

    @Test
    void save() {
        firestationRepository.save(new Firestation());
        assertEquals(14, firestationRepository.getFirestationList().size());
    }

    @Test
    void update() {
        assertEquals("3", firestationRepository.getFirestationList().get(0).getStation());
        firestationRepository.update(Firestation.builder().address("1509 Culver St").station("5").build());
        assertEquals("5", firestationRepository.getFirestationList().get(0).getStation());
    }
}