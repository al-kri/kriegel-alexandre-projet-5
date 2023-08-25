package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    private final List<MedicalRecord> medicalRecordList = JsonData.medicalRecords;

    @Override
    public List<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName) {
        return medicalRecordList
                .stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
                .toList();
    }
}
