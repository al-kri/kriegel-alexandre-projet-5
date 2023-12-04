package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicalRecordRepositoryImplTest {

    private MedicalRecordRepositoryImpl medicalRecordRepository;

    @BeforeEach
    public void beforeEach() {
        JsonData.loadJsonData();
        medicalRecordRepository = new MedicalRecordRepositoryImpl();
        assertEquals(23, JsonData.medicalRecords.size());
        assertEquals(23, medicalRecordRepository.findAll().size());
    }

    @Test
    void delete() {
        medicalRecordRepository.delete("John", "Boyd");
        assertEquals(22, medicalRecordRepository.findAll().size());
    }

    @Test
    void findAll() {
        medicalRecordRepository.findAll();
        assertEquals(23, medicalRecordRepository.findAll().size());
    }

    @Test
    void findByPersonList() {
        final var personList = List.of(Person.builder()
                .firstName("John")
                .lastName("Boyd")
                .build());
        medicalRecordRepository.findByPersonList(personList);
        assertEquals("John", personList.get(0).getFirstName());
        assertEquals("Boyd", personList.get(0).getLastName());
    }

    @Test
    void save() {
        medicalRecordRepository.save(new MedicalRecord());
        assertEquals(24, medicalRecordRepository.findAll().size());

    }

    @Test
    void update() {
        final var medicalRecordUpdated = MedicalRecord.builder()
                .firstName("John")
                .lastName("Boyd")
                .medications(List.of("aznol:350mg",
                        "hydrapermazol:100mg", "terazine:10mg"))
                .allergies(List.of("nillacilan", "peanut"))
                .build();
        medicalRecordRepository.update(medicalRecordUpdated);
        assertEquals(List.of("aznol:350mg",
                "hydrapermazol:100mg",
                "terazine:10mg"), medicalRecordUpdated.getMedications());
        assertEquals(List.of("nillacilan", "peanut"), medicalRecordUpdated.getAllergies());
    }
}