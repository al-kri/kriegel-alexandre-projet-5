package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.repository.FirestationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FirestationServiceImplTest {

    @InjectMocks
    private FirestationServiceImpl firestationService;

    @Mock
    private FirestationRepository firestationRepository;

    @Test
    void delete() {
        assertDoesNotThrow(() -> firestationService.delete("address", "station"));
        Mockito.verify(firestationRepository).delete(any(), any());
    }

    @Test
    void findAll() {
        assertDoesNotThrow(() -> firestationService.findAll());
        Mockito.verify(firestationRepository).findAll();
    }

    @Test
    void findByAddress() {
        assertDoesNotThrow(() -> firestationService.findByAddress("address"));
        Mockito.verify(firestationRepository).findByAddress(any());
    }

    @Test
    void findByStation() {
        assertDoesNotThrow(() -> firestationService.findByStation("station number"));
        Mockito.verify(firestationRepository).findByStation(any());
    }

    @Test
    void save() {
        assertDoesNotThrow(() -> firestationService.save(new Firestation()));
        Mockito.verify(firestationRepository).save(any());
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> firestationService.update(Firestation.builder().build()));
        Mockito.verify(firestationRepository).update(any());
    }
}