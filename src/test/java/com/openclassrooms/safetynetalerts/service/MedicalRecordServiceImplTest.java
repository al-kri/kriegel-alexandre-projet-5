package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceImplTest {

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordService;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    void delete() {
        assertDoesNotThrow(() -> medicalRecordService.delete("firstName", "lastName"));
        Mockito.verify(medicalRecordRepository).delete(anyString(), anyString());
    }

    @Test
    void findAll() {
        assertDoesNotThrow(() -> medicalRecordService.findAll());
        Mockito.verify(medicalRecordRepository).findAll();
    }

    @Test
    void findByPersonList() {
        assertDoesNotThrow(() -> medicalRecordService.findByPersonList(anyList()));
        Mockito.verify(medicalRecordRepository).findByPersonList(anyList());
    }

    @Test
    void save() {
        assertDoesNotThrow(() -> medicalRecordService.save(new MedicalRecord()));
        Mockito.verify(medicalRecordRepository).save(any());
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> medicalRecordService.update(MedicalRecord.builder().build()));
        Mockito.verify(medicalRecordRepository).update(any());
    }
}