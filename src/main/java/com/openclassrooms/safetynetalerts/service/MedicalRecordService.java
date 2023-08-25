package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName);
}
